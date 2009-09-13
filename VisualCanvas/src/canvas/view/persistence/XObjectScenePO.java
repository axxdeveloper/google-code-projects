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
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author Isaac
 */
public class XObjectScenePO {

    private List<XWidgetPO> children;

    public List<XWidgetPO> getChildren() {
        if ( children == null ) {
            children = new LinkedList<XWidgetPO>();
        }
        return children;
    }

    public void setChildren(List<XWidgetPO> children) {
        this.children = children;
    }

    public void addChild(XWidgetPO xWidgetPO) {
        getChildren().add( xWidgetPO );
    }
    
    public XObjectScene createXObjectScene() {
        return XObjectScene.newInstance( this );
    }
    
    public XObjectScenePO cloneXObjectScenePO() {
        XObjectScenePO clonePO = new XObjectScenePO();
        for (XWidgetPO xWidgetPO : getChildren()) {
            clonePO.addChild(xWidgetPO.clonePO());
        }
        return clonePO;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof XObjectScenePO == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        boolean isSuperEquals = super.equals(obj);
        boolean isEquals = isSuperEquals;
        if ( isSuperEquals ) {
            XObjectScenePO po = (XObjectScenePO) obj;
            isEquals = new EqualsBuilder()
                    .append(po.getChildren(), getChildren())
                    .isEquals();            
        } 
        return isEquals;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.children != null ? this.children.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return new ToStringBuilder( this )
                .appendSuper( super.toString() )
                .append( "children", children )
                .toString();
    }
    
}
