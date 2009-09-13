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

import canvas.view.materials.LabelWidgetEditor;
import canvas.view.persistence.ColorPO;
import canvas.view.persistence.PointPO;
import canvas.view.persistence.RectanglePO;
import canvas.view.persistence.XEditableLabelWidgetPO;
import canvas.view.persistence.XWidgetPO;
import java.awt.Color;
import java.util.UUID;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.modules.visual.border.RoundedBorder;

/**
 *
 * @author Isaac
 */

public class XEditableLabelWidget extends LabelWidget implements XWidget {

    private UUID widgetID;
    private Color fontColor = Color.GRAY;
    
    private XEditableLabelWidget(ObjectScene objectScene, String text) {
        super( objectScene, text );
        setAlignment (LabelWidget.Alignment.CENTER);
        setVerticalAlignment (LabelWidget.VerticalAlignment.CENTER);
        getActions().addAction(ActionFactory.createInplaceEditorAction(new LabelWidgetEditor()));            
    }
    
    public static XEditableLabelWidget newInstance(XObjectScene objectScene, XEditableLabelWidgetPO widgetPO) {
        XEditableLabelWidget result = new XEditableLabelWidget(objectScene, widgetPO.getLabel());
        objectScene.getMainLayer().addChild( result );
        objectScene.addObject( widgetPO.getWidgetID(), result );
        result.changeColor( widgetPO.getWidgetColor().toColor() );
        result.setWidgetID( widgetPO.getWidgetID() );
        if ( !widgetPO.isPointer() ) {
            result.setBorder(BorderFactory.createRoundedBorder(3, 3, null, Color.LIGHT_GRAY));
        } 
        if ( widgetPO.getPreferredBounds() != null ) {
            result.setPreferredBounds( widgetPO.getPreferredBounds().toRectangle() );
        }
        result.setPreferredLocation( widgetPO.getPreferredLocation().toPoint() );
        objectScene.validate();
        return result;
    }
    
    public XWidgetPO complete() {
        XEditableLabelWidgetPO editableLabelWidgetPO = new XEditableLabelWidgetPO();
        editableLabelWidgetPO.setLabel( getLabel() );
        editableLabelWidgetPO.setWidgetID( getWidgetID() );
        if ( ! (getBorder() instanceof RoundedBorder) ) {
            editableLabelWidgetPO.setPointer( true );
        }
        if ( isPreferredBoundsSet() ) {
            editableLabelWidgetPO.setPreferredBounds( new RectanglePO( getPreferredBounds() ) );
        }
        editableLabelWidgetPO.setPreferredLocation( new PointPO(getPreferredLocation()) );
        editableLabelWidgetPO.setWidgetColor( new ColorPO(currentColor()) );
        return editableLabelWidgetPO;
    }

    public void setWidgetID(UUID widgetID) {
        this.widgetID = widgetID;
    }

    public UUID getWidgetID() {
        return widgetID;
    }

    public void changeColor(Color color) {
        if ( color != null ) {
            fontColor = color;
            setForeground( fontColor );
            setBackground( fontColor );
        }
    }

    public Color currentColor() {
        if ( fontColor == null ) {
            fontColor = Color.GRAY;
        }
        return fontColor;
    }
    
}
