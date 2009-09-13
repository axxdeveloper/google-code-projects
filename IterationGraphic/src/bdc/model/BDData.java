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

package bdc.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Isaac
 */
public abstract class BDData {

    public static final String PROP_NAME = "name";
    public static final String PROP_DESCRIPTION = "description";

    private String name = "";
    private String description = "";
    
    public String getName() {
        if ( name == null ) {
            name = "";
        }
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange(PROP_NAME, oldName, name);
    }

    public String getDescription() {
        if ( description == null ) {
            description = "";
        }
        return description;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        changeSupport.firePropertyChange(PROP_DESCRIPTION, oldDescription, description);
    }

    public final PropertyChangeSupport changeSupport = new PropertyChangeSupport( this );

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
