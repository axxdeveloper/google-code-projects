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

import canvas.view.locator.XWidgetLocator;
import canvas.view.widget.XObjectScene;
import java.awt.Point;
import java.awt.Rectangle;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.action.WidgetAction.State;
import org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Isaac
 */
public class LocateWidgetAction extends WidgetAction.Adapter {

    private Point pressedPoint;
    private Point releasedPoint;
    
    @Override
    public State mousePressed(Widget widget, WidgetMouseEvent evt) {
        XObjectScene objectScene = (XObjectScene) widget.getScene();
        if ( objectScene.getXWidgetLocator() != null ) {
            pressedPoint = evt.getPoint();
            return State.CONSUMED;
        } else {
            return super.mousePressed(widget, evt);
        }
    }

    @Override
    public State mouseReleased(Widget widget, WidgetMouseEvent evt) {
        XObjectScene objectScene = (XObjectScene) widget.getScene();
        XWidgetLocator xWidgetLocator = objectScene.getXWidgetLocator();
        if ( xWidgetLocator != null ) {
            releasedPoint = evt.getPoint();
            int x = pressedPoint.x < releasedPoint.x ? pressedPoint.x : releasedPoint.x;
            int y = pressedPoint.y < releasedPoint.y ? pressedPoint.y : releasedPoint.y;
            int width = Math.abs( pressedPoint.x - releasedPoint.x );
            int height = Math.abs( pressedPoint.y - releasedPoint.y );
            Point location = new Point(x, y);
            Rectangle bounds = new Rectangle(x, y, width, height);
            if ( xWidgetLocator != null ) {
                if ( bounds.isEmpty() ) {
                    xWidgetLocator.locate(objectScene, location, null);
                } else {
                    xWidgetLocator.locate(objectScene, location, bounds);
                }
            }
            return State.CONSUMED;            
        } else {
            return super.mouseReleased(widget, evt);
        }
    }

}
