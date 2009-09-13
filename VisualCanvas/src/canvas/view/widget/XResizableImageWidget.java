/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package canvas.view.widget;

import canvas.view.helper.ActionInitiator;
import canvas.view.helper.ImageHelper;
import canvas.view.persistence.ColorPO;
import canvas.view.persistence.PointPO;
import canvas.view.persistence.RectanglePO;
import canvas.view.persistence.XResizableImageWidgetPO;
import canvas.view.persistence.XWidgetPO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.UUID;
import org.netbeans.api.visual.widget.ImageWidget;

/**
 * Cancel paintAsDisable and just paint to fit client area.
 * @author Isaac
 */
public class XResizableImageWidget extends ImageWidget implements XWidget {

    private UUID widgetID;
    
    private XResizableImageWidget(XObjectScene objectScene, BufferedImage bimg) {
        super(objectScene, bimg);
    }

    public static XResizableImageWidget newInstance(XObjectScene objectScene, XResizableImageWidgetPO widgetPO) {
        XResizableImageWidget widget = new XResizableImageWidget(objectScene, ImageHelper.toBufferedImage(widgetPO.getImageBase64Text()));
        widget.changeColor( widgetPO.getWidgetColor().toColor() );
        widget.setWidgetID( widgetPO.getWidgetID() );
        if ( widgetPO.getPreferredBounds() != null ) {
            widget.setPreferredBounds( widgetPO.getPreferredBounds().toRectangle() );
        }
        widget.setPreferredLocation( widgetPO.getPreferredLocation().toPoint() );
        objectScene.getMainLayer().addChild(widget);
        objectScene.addObject(widgetPO.getWidgetID(), widget);
        ActionInitiator.initDefaultAction(objectScene, widget);
        objectScene.validate();
        return widget;
    }
    
    public XWidgetPO complete() {
        XResizableImageWidgetPO resizableImageWidgetPO = new XResizableImageWidgetPO();
        resizableImageWidgetPO.setWidgetID( getWidgetID() );
        resizableImageWidgetPO.setImageBase64Text(ImageHelper.toBase64Text((BufferedImage)getImage()));
        if ( isPreferredBoundsSet() ) {
            resizableImageWidgetPO.setPreferredBounds( new RectanglePO(getPreferredBounds()) );
        }
        resizableImageWidgetPO.setPreferredLocation( new PointPO(getPreferredLocation()) );
        resizableImageWidgetPO.setWidgetColor( new ColorPO(currentColor()) );
        return resizableImageWidgetPO;
    }

    @Override
    protected void paintWidget() {
        Image image = getImage();
        if (image == null) {
            return;
        }
        Graphics2D gr = getGraphics();
        Rectangle clientArea = getClientArea();
        gr.drawImage(image, clientArea.x, clientArea.y, clientArea.width, clientArea.height, null);
    }

    /** @see #currentColor() */
    public void changeColor(Color color) {
        if ( color != null ) {
            setBackground(color);
        }
    }

    /**
     * Image's color is background - this definition is not seriously, just is a painless choice.<br/>
     * You can change image's widget color any time, any way. If it is needed.
     */
    public Color currentColor() {
        Paint bg = getBackground();
        if ( bg instanceof Color ) {
            return (Color) bg;
        } else {
            return Color.WHITE;
        }
    }

    public void setWidgetID(UUID widgetID) {
        this.widgetID = widgetID;
    }

    public UUID getWidgetID() {
        return widgetID;
    }

}