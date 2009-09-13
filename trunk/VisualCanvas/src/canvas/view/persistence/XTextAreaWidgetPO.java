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

package canvas.view.persistence;

import canvas.view.widget.XObjectScene;
import canvas.view.widget.XTextAreaWidget;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 *
 * @author Isaac
 */
public class XTextAreaWidgetPO extends XWidgetPO<XTextAreaWidget> {
    
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public XTextAreaWidget createWidget(XObjectScene objectScene) {
        return XTextAreaWidget.newInstance(objectScene, this);
    }
    
    @Override
    public XWidgetPO clonePO() {
        XTextAreaWidgetPO clonePO = new XTextAreaWidgetPO();
        clonePO.setWidgetID(getWidgetID());
        clonePO.setPreferredBounds(getPreferredBounds());
        clonePO.setPreferredLocation(getPreferredLocation());
        clonePO.setText(getText());
        return clonePO;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof XTextAreaWidgetPO == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        boolean isSuperEquals = super.equals(obj);
        boolean isEquals = isSuperEquals;
        if ( isSuperEquals ) {
            XTextAreaWidgetPO po = (XTextAreaWidgetPO) obj;
            isEquals = new EqualsBuilder()
                    .append(po.getText(), getText())
                    .isEquals();            
        } 
        return isEquals;
    }
    
}
