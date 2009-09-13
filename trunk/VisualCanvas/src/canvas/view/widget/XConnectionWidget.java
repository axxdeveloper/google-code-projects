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
import canvas.view.helper.BorderHelper;
import canvas.view.persistence.ColorPO;
import canvas.view.persistence.XConnectionWidgetPO;
import canvas.view.persistence.XWidgetPO;
import java.awt.Color;
import java.util.UUID;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.anchor.PointShape;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Isaac
 */
public class XConnectionWidget extends ConnectionWidget implements XWidget {

    private UUID widgetID;
    
    private XConnectionWidget(ObjectScene objectScene) {
        super( objectScene );
        setBorder( BorderHelper.chooseBorder(BorderHelper.BORDER_TYPE.EMPTY));
        setTargetAnchorShape(AnchorShape.TRIANGLE_FILLED);
        setEndPointShape(PointShape.SQUARE_FILLED_BIG);
    }
    
    public static XConnectionWidget newInstance(XObjectScene objectScene, XConnectionWidgetPO connWidgetPO) {
        XConnectionWidget connWidget = new XConnectionWidget( objectScene );
        Widget sourceWidget = objectScene.findWidget( connWidgetPO.getSourceID() );
        Widget targetWidget = objectScene.findWidget( connWidgetPO.getTargetID() );
        objectScene.getConnLayer().addChild( connWidget );
        if ( objectScene.findWidget( connWidgetPO.getSourceID() ) != null ) {
            objectScene.removeObject( connWidgetPO.getSourceID() );
        }
        if ( objectScene.findWidget( connWidgetPO.getTargetID() ) != null ) {
            objectScene.removeObject( connWidgetPO.getTargetID() );
        }
        objectScene.addObject( connWidgetPO.getWidgetID(), connWidget, sourceWidget, targetWidget );
        ActionInitiator.initNotationWidgetAction( objectScene, targetWidget, sourceWidget, connWidget);
        connWidget.changeColor(connWidgetPO.getWidgetColor().toColor());
        connWidget.setWidgetID(connWidgetPO.getWidgetID());
        connWidget.setTargetAnchor(AnchorFactory.createCircularAnchor(targetWidget, 0));
        connWidget.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceWidget));
        objectScene.validate();
        return connWidget;
    }

    public XWidgetPO complete() {
        XConnectionWidgetPO connectionWidgetPO = new XConnectionWidgetPO();
        connectionWidgetPO.setWidgetColor( new ColorPO(currentColor()) );
        connectionWidgetPO.setWidgetID( getWidgetID() );
        connectionWidgetPO.setSourceID( ((XWidget) getSourceAnchor().getRelatedWidget()).getWidgetID() );
        connectionWidgetPO.setTargetID( ((XWidget) getTargetAnchor().getRelatedWidget()).getWidgetID() );
        return connectionWidgetPO;
    }

    public void setWidgetID(UUID widgetID) {
        this.widgetID = widgetID;
    }

    public UUID getWidgetID() {
        return widgetID;
    }

    public void changeColor(Color color) {
        if ( color != null ) {
            setLineColor(color);
        }
    }

    public Color currentColor() {
        return getLineColor();
    }

}
