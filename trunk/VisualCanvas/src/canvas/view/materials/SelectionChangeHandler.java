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

import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.netbeans.api.visual.border.Border;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.model.ObjectSceneEvent;
import org.netbeans.api.visual.model.ObjectSceneListener;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.ComponentWidget;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Isaac
 */
public class SelectionChangeHandler implements ObjectSceneListener {
    
    private Map<Widget, Border> borderMap = new LinkedHashMap<Widget, Border>();

    public void selectionChanged(ObjectSceneEvent event, Set<Object> previousSelection, Set<Object> newSelection) {
        for (Object selection : previousSelection) {
            for (Widget widget : event.getObjectScene().findWidgets(selection)) {
                handleComponentWidget( widget, false );
                if ( borderMap.get(widget) != null ) {
                    widget.setBorder(borderMap.get(widget));
                    Rectangle oldBounds = widget.getPreferredBounds();
                    Rectangle newBounds = new Rectangle( oldBounds.x + 10, oldBounds.y + 10, oldBounds.width - 20, oldBounds.height - 20);
                    widget.setPreferredBounds(newBounds);
                    borderMap.remove(widget);
                }
            }
        }
        for (Object selection : newSelection) {
            for (Widget widget : event.getObjectScene().findWidgets(selection)) {
                handleComponentWidget( widget, true );
                if ( ! ( widget instanceof ConnectionWidget ) ) {
                    borderMap.put(widget, widget.getBorder());
                    Rectangle oldBounds = widget.getPreferredBounds();
                    Rectangle newBounds = new Rectangle(oldBounds.x - 10, oldBounds.y - 10, oldBounds.width + 20, oldBounds.height + 20);
                    widget.setPreferredBounds(newBounds);
                    widget.setBorder( BorderFactory.createResizeBorder(10, Color.BLUE, false) );                    
                }
            }
        }
    }

    /** In order to hide ComponentWidget's JComponent while the one is under other widgets.
     * By calling this method, JComponent's swing action will be no effect, it will need WidgetActions.
     * Reference: http://graph.netbeans.org/servlets/ReadMsg?list=users&msgNo=726 
     * @see com.inqgen.iqnote.component.canvas.view.materials.ComponentWidgetStateChangedHandler */
    private void handleComponentWidget(Widget widget, boolean isNewSelection) {
        if ( widget instanceof ComponentWidget ) {
            ComponentWidget compWidget = (ComponentWidget) widget;
            if ( compWidget.isComponentVisible() ) {
                compWidget.setComponentVisible( false );
            } else {
                compWidget.setComponentVisible( true );
            }
        }
    }

    public void objectAdded(ObjectSceneEvent arg0, Object arg1) {
        throw new UnsupportedOperationException("SelectionChangeHandler don't support " + arg0.toString());
    }

    public void objectRemoved(ObjectSceneEvent arg0, Object arg1) {
        throw new UnsupportedOperationException("SelectionChangeHandler don't support " + arg0.toString());
    }

    public void objectStateChanged(ObjectSceneEvent arg0, Object arg1, ObjectState arg2, ObjectState arg3) {
        throw new UnsupportedOperationException("SelectionChangeHandler don't support " + arg0.toString());
    }

    public void highlightingChanged(ObjectSceneEvent arg0, Set<Object> arg1, Set<Object> arg2) {
        throw new UnsupportedOperationException("SelectionChangeHandler don't support " + arg0.toString());
    }

    public void hoverChanged(ObjectSceneEvent arg0, Object arg1, Object arg2) {
        throw new UnsupportedOperationException("SelectionChangeHandler don't support " + arg0.toString());
    }

    public void focusChanged(ObjectSceneEvent arg0, Object arg1, Object arg2) {
        throw new UnsupportedOperationException("SelectionChangeHandler don't support " + arg0.toString());
    }
    
}
