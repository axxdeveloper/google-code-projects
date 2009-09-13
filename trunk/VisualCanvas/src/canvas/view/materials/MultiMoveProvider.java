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

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.netbeans.api.visual.action.MoveProvider;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Isaac
 */
public class MultiMoveProvider implements MoveProvider {

    private Map<Widget, Point> originals = new HashMap<Widget, Point> ();
    private Point original;
    
    public void movementStarted(Widget widget) {
        ObjectScene scene = (ObjectScene) widget.getScene();
        Set<?> selectedObjects = scene.getSelectedObjects();
        for (Object o : selectedObjects) {
            Widget w = scene.findWidget (o);
            if (w != null) {
                Point location = w.getPreferredLocation ();
                originals.put (w, location == null ? new Point(0, 0) : location);
            }
        }
    }

    public void movementFinished(Widget widget) {
        originals.clear ();
        original = null;
    }

    public Point getOriginalLocation(Widget widget) {
        Point location = widget.getPreferredLocation();
        original = location == null ? new Point(0, 0) : location;
        return original;
    }

    public void setNewLocation(Widget widget, Point location) {
        int dx = location.x - original.x;
        int dy = location.y - original.y;
        for (Map.Entry<Widget, Point> entry : originals.entrySet ()) {
            Point point = entry.getValue ();
            entry.getKey ().setPreferredLocation (new Point (point.x + dx, point.y + dy));
        }
    }

}
