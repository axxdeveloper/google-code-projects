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

package canvas.view.materials;

import java.util.List;
import java.util.Set;
import org.netbeans.api.visual.model.ObjectSceneEvent;
import org.netbeans.api.visual.model.ObjectSceneListener;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.ComponentWidget;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Isaac
 */
public class ComponentWidgetStateChangedHandler implements ObjectSceneListener {

    /** In order to hide ComponentWidget's JComponent while the one is under other widgets.
     * By calling this method, JComponent's swing action will be no effect, it will need WidgetActions.
     * Reference: http://graph.netbeans.org/servlets/ReadMsg?list=users&msgNo=726 
     * @see com.inqgen.iqnote.component.canvas.view.materials.SelectionChangeHandler */
    public void objectStateChanged(ObjectSceneEvent evt, Object changedObject, ObjectState previousState, ObjectState newState) {
        if ( !newState.isSelected() && newState.isHovered() ) {
            List<Widget> widgets = evt.getObjectScene().findWidgets(changedObject);
            for (Widget widget : widgets) {
                if ( widget instanceof ComponentWidget ) {
                    ComponentWidget compWidget = (ComponentWidget) widget;
                    if ( compWidget.isComponentVisible() ) {
                        compWidget.setComponentVisible( false );
                    }
                }            
            }            
        }
    }

    public void objectAdded(ObjectSceneEvent arg0, Object arg1) {
        throw new UnsupportedOperationException("ComponentWidgetStateChangedHandler don't support " + arg0.toString());
    }

    public void objectRemoved(ObjectSceneEvent arg0, Object arg1) {
        throw new UnsupportedOperationException("ComponentWidgetStateChangedHandler don't support " + arg0.toString());
    }

    public void selectionChanged(ObjectSceneEvent arg0, Set<Object> arg1, Set<Object> arg2) {
        throw new UnsupportedOperationException("ComponentWidgetStateChangedHandler don't support " + arg0.toString());
    }

    public void highlightingChanged(ObjectSceneEvent arg0, Set<Object> arg1, Set<Object> arg2) {
        throw new UnsupportedOperationException("ComponentWidgetStateChangedHandler don't support " + arg0.toString());
    }

    public void hoverChanged(ObjectSceneEvent arg0, Object arg1, Object arg2) {
        throw new UnsupportedOperationException("ComponentWidgetStateChangedHandler don't support " + arg0.toString());
    }

    public void focusChanged(ObjectSceneEvent arg0, Object arg1, Object arg2) {
        throw new UnsupportedOperationException("ComponentWidgetStateChangedHandler don't support " + arg0.toString());
    }

}
