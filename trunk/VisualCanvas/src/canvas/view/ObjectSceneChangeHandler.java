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
package canvas.view;

import canvas.utils.POHelper;
import canvas.view.widget.XObjectScene;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Isaac
 */
public class ObjectSceneChangeHandler implements PropertyChangeListener {

    private JPanel jPanelScene;
    
    public ObjectSceneChangeHandler(JPanel jPanelScene) {
        this.jPanelScene = jPanelScene;
    }
    
    public void propertyChange(PropertyChangeEvent evt) {
        if ( POHelper.sourceChanged(evt.getOldValue(), evt.getNewValue()) && evt.getNewValue() instanceof XObjectScene ) {
            XObjectScene objectScene = (XObjectScene) evt.getNewValue();
            JComponent jComp = objectScene.createView();
            jPanelScene.add( jComp );
            objectScene.validate();
            jPanelScene.repaint();
        }
    }

}
