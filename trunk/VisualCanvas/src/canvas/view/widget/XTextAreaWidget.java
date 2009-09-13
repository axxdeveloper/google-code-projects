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

import canvas.view.materials.XTextAreaWidgetDocumentHandler;
import canvas.view.persistence.ColorPO;
import canvas.view.persistence.PointPO;
import canvas.view.persistence.RectanglePO;
import canvas.view.persistence.XTextAreaWidgetPO;
import canvas.view.persistence.XWidgetPO;
import java.awt.Color;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import org.apache.commons.lang.StringEscapeUtils;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.ComponentWidget;

/**
 *
 * @author Isaac
 */
public class XTextAreaWidget extends ComponentWidget implements XWidget {

    private UUID widgetID;
    private Color fontColor = Color.GRAY;
    
    private XTextAreaWidget(ObjectScene objectScene, JComponent comp) {
        super(objectScene, comp);
        setOpaque(false);
        setBorder(BorderFactory.createRoundedBorder(3, 3, null, Color.LIGHT_GRAY));
    }
    
    public static XTextAreaWidget newInstance(XObjectScene objectScene, XTextAreaWidgetPO widgetPO) {
        JTextArea jTextArea = new JTextArea(4, 20);
        jTextArea.setOpaque(false);
        jTextArea.setLineWrap(true);
        
        XTextAreaWidget descWidget = new XTextAreaWidget( objectScene, jTextArea );
        jTextArea.getDocument().addDocumentListener(new XTextAreaWidgetDocumentHandler(objectScene, descWidget));
        descWidget.changeColor( widgetPO.getWidgetColor().toColor() );
        if ( widgetPO.getPreferredBounds() != null ) {
            descWidget.setPreferredBounds( widgetPO.getPreferredBounds().toRectangle() );
        }
        descWidget.setPreferredLocation( widgetPO.getPreferredLocation().toPoint() );
        descWidget.setWidgetID( widgetPO.getWidgetID() );
        ((JTextArea) descWidget.getComponent()).setText( StringEscapeUtils.unescapeJava(widgetPO.getText()) );
        objectScene.getMainLayer().addChild( descWidget );
        objectScene.addObject( widgetPO.getWidgetID(), descWidget );
        objectScene.validate();
        return descWidget;
    }

    public XWidgetPO complete() {
        XTextAreaWidgetPO textAreaWidgetPO = new XTextAreaWidgetPO();
        textAreaWidgetPO.setWidgetID( getWidgetID() );
        if ( isPreferredBoundsSet() ) {
            textAreaWidgetPO.setPreferredBounds( new RectanglePO(getPreferredBounds()) );
        }
        textAreaWidgetPO.setWidgetColor( new ColorPO(currentColor()) );
        textAreaWidgetPO.setPreferredLocation( new PointPO(getPreferredLocation()) );
        textAreaWidgetPO.setText( StringEscapeUtils.escapeJava(((JTextArea)getComponent()).getText()) );
        return textAreaWidgetPO;
    }

    public void changeColor(Color color) {
        if ( color != null ) {
            fontColor = color;
            JTextArea jTextArea = (JTextArea) getComponent();
            jTextArea.setForeground( fontColor );            
            jTextArea.setBackground( fontColor );
        }
    }

    public Color currentColor() {
        if ( fontColor == null ) {
            fontColor = Color.GRAY;
        }
        return fontColor;
    }

    public void setWidgetID(UUID widgetID) {
        this.widgetID = widgetID;
    }

    public UUID getWidgetID() {
        return widgetID;
    }
    
}
