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
package canvas.model;

import canvas.view.persistence.XObjectScenePO;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 *
 * @author Isaac
 */
public class XCanvasPO {

    public static final String PROP_OBJECT_SCENE_PO = "objectScenePO";
    public static final String PROP_SCENE_BASE64 = "sceneImageBase64ForPrint";
    
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport( this );
    
    private String sceneImageBase64ForPrint;
    private XObjectScenePO objectScenePO;
    
   

    public XObjectScenePO getObjectScenePO() {
        if ( objectScenePO == null ) {
            objectScenePO = new XObjectScenePO();
        }
        return objectScenePO;
    }

    public void setObjectScenePO(XObjectScenePO objectScenePO) {
        XObjectScenePO oldObjectScenePO = this.objectScenePO;
        this.objectScenePO = objectScenePO;
        changeSupport.firePropertyChange( PROP_OBJECT_SCENE_PO, oldObjectScenePO, objectScenePO );
    }

    public String getSceneImageBase64ForPrint() {
        if ( sceneImageBase64ForPrint == null ) {
            sceneImageBase64ForPrint = "";
        }
        return sceneImageBase64ForPrint;
    }

    public void setSceneImageBase64ForPrint(String sceneImageBase64ForPrint) {
        String oldSceneImageBase64ForPrint = this.sceneImageBase64ForPrint;
        this.sceneImageBase64ForPrint = sceneImageBase64ForPrint;
        changeSupport.firePropertyChange( PROP_SCENE_BASE64, oldSceneImageBase64ForPrint, sceneImageBase64ForPrint );
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("objectScenePO", objectScenePO.toString() )
                .append("sceneImageBase64ForPrint", sceneImageBase64ForPrint )
                .toString();
    }
    
    public void addPropertyChangeListener( PropertyChangeListener listener ) {
        changeSupport.addPropertyChangeListener( listener );
    }

    public void addPropertyChangeListener( String propertyName, PropertyChangeListener listener ) {
        changeSupport.addPropertyChangeListener( propertyName, listener );
    }

    public void removePropertyChangeListener( PropertyChangeListener listener ) {
        changeSupport.removePropertyChangeListener( listener );
    }

    public void removePropertyChangeListener( String propertyName, PropertyChangeListener listener ) {
        changeSupport.removePropertyChangeListener( propertyName, listener );
    }

    public void firePropertyChange( String propertyName, Object oldValue, Object newValue ) {
        changeSupport.firePropertyChange( propertyName, oldValue, newValue );
    }
}
