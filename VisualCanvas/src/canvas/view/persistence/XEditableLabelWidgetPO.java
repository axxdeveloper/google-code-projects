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

import canvas.view.widget.XEditableLabelWidget;
import canvas.view.widget.XObjectScene;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author Isaac
 */
public class XEditableLabelWidgetPO extends XWidgetPO<XEditableLabelWidget> {

    private boolean pointer = false;
    private String label = "";

    @Override
    public XEditableLabelWidget createWidget(XObjectScene objectScene) {
        return XEditableLabelWidget.newInstance(objectScene, this);
    }

    @Override
    public XWidgetPO clonePO() {
        XEditableLabelWidgetPO clonePO = new XEditableLabelWidgetPO();
        clonePO.setWidgetID(getWidgetID());
        clonePO.setPointer(isPointer());
        clonePO.setPreferredBounds(getPreferredBounds());
        clonePO.setPreferredLocation(getPreferredLocation());
        clonePO.setLabel(getLabel());
        return clonePO;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof XEditableLabelWidgetPO == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        boolean isSuperEquals = super.equals(obj);
        boolean isEquals = isSuperEquals;
        if ( isSuperEquals ) {
            XEditableLabelWidgetPO po = (XEditableLabelWidgetPO) obj;
            isEquals = new EqualsBuilder()
                    .append(po.getLabel(), getLabel())
                    .isEquals();            
        } 
        return isEquals;
    }

    /** A pointer label will have empty border */
    public boolean isPointer() {
        return pointer;
    }

    public void setPointer(boolean pointer) {
        this.pointer = pointer;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
