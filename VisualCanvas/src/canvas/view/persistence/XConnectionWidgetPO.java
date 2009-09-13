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

import canvas.view.widget.XConnectionWidget;
import canvas.view.widget.XObjectScene;
import java.util.UUID;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author Isaac
 */
public class XConnectionWidgetPO extends XWidgetPO<XConnectionWidget> {

    private UUID sourceID;
    private UUID targetID;

    public UUID getSourceID() {
        return sourceID;
    }

    public void setSourceID(UUID sourceID) {
        this.sourceID = sourceID;
    }

    public UUID getTargetID() {
        return targetID;
    }

    public void setTargetID(UUID targetID) {
        this.targetID = targetID;
    }

    @Override
    public XConnectionWidget createWidget(XObjectScene objectScene) {
        return XConnectionWidget.newInstance( objectScene, this );
    }
    
    @Override
    public XWidgetPO clonePO() {
        XConnectionWidgetPO clonePO = new XConnectionWidgetPO();
        clonePO.setWidgetID(getWidgetID());
        clonePO.setPreferredBounds(getPreferredBounds());
        clonePO.setPreferredLocation(getPreferredLocation());
        clonePO.setSourceID(getSourceID());
        clonePO.setTargetID(getTargetID());
        return clonePO;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof XConnectionWidgetPO == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        boolean isSuperEquals = super.equals(obj);
        boolean isEquals = isSuperEquals;
        if ( isSuperEquals ) {
            XConnectionWidgetPO po = (XConnectionWidgetPO) obj;
            isEquals = new EqualsBuilder()
                    .append(po.getSourceID(), getSourceID())
                    .append(po.getTargetID(), getTargetID())
                    .isEquals();            
        } 
        return isEquals;
    }
    
}
