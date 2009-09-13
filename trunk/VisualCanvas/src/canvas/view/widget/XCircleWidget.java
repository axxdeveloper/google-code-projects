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

import canvas.view.persistence.ColorPO;
import canvas.view.persistence.PointPO;
import canvas.view.persistence.RectanglePO;
import canvas.view.persistence.XCircleWidgetPO;
import canvas.view.persistence.XWidgetPO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.UUID;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.modules.visual.border.ResizeBorder;

/**
 *
 * @author Isaac
 */
public class XCircleWidget extends LabelWidget implements XWidget {

    private UUID widgetID;
    private Color circleColor = Color.GRAY;
    
    private XCircleWidget(ObjectScene objectScene) {
        super( objectScene );
    }
    
    public static XCircleWidget newInstance(XObjectScene objectScene, XCircleWidgetPO widgetPO) {
        if ( objectScene == null || widgetPO == null ) {
            throw new IllegalArgumentException("Parameters can't be null.");
        }
        XCircleWidget result = new XCircleWidget(objectScene);
        result.changeColor( widgetPO.getWidgetColor().toColor() );
        objectScene.getMainLayer().addChild( result );
        objectScene.addObject( widgetPO.getWidgetID(), result );
        result.setWidgetID( widgetPO.getWidgetID() );
        if ( widgetPO.getPreferredBounds() != null ) {
            result.setPreferredBounds( widgetPO.getPreferredBounds().toRectangle() );
        }
        result.setPreferredLocation( widgetPO.getPreferredLocation().toPoint() );
        objectScene.validate();        
        return result;
    }

    /**
     * Paint circle by resizing widget bounds.
     * @see com.inqgen.iqnote.component.canvas.view.materials.SelectionChangeHandler#selectionChanged
     */
    @Override
    protected void paintWidget() {
        super.paintWidget();
        Graphics2D g2 = getGraphics();
        Rectangle bounds = getPreferredBounds();
        boolean isResizeBorder = getBorder().getClass() == ResizeBorder.class;
        /** + 11 or -22 or -1 here are just adjust to fit size  */
        double x = isResizeBorder ? bounds.getX() + 11 : bounds.getX();
        double y = isResizeBorder ? bounds.getY() + 11 : bounds.getY();
        double width = isResizeBorder ? bounds.getWidth() - 22 : bounds.getWidth() - 1; // 22 because right side will be cut. (original is 20)
        double height = isResizeBorder ? bounds.getHeight() - 22 : bounds.getHeight() - 1;
        Ellipse2D ellipse2D = new Ellipse2D.Double( x, y, width, height);
        g2.setPaint( currentColor() );
        g2.draw( ellipse2D );
    }
    
    public void setWidgetID(UUID widgetID) {
        this.widgetID = widgetID;
    }

    public UUID getWidgetID() {
        return widgetID;
    }

    public XWidgetPO complete() {
        XCircleWidgetPO circleWidgetPO = new XCircleWidgetPO();
        circleWidgetPO.setWidgetID( getWidgetID() );
        if ( isPreferredBoundsSet() ) {
            circleWidgetPO.setPreferredBounds( new RectanglePO( getPreferredBounds() ) );
        }
        circleWidgetPO.setPreferredLocation( new PointPO(getPreferredLocation()) );
        circleWidgetPO.setWidgetColor( new ColorPO(currentColor()) );
        return circleWidgetPO;
    }

    public void changeColor(Color color) {
        if ( color != null ) {
            circleColor = color;
        }
    }

    public Color currentColor() {
        if ( circleColor == null ) {
            circleColor = Color.GRAY;
        }
        return circleColor;
    }

}
