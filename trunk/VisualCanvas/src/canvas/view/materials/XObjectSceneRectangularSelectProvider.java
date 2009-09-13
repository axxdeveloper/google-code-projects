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
import java.awt.Rectangle;
import org.netbeans.api.visual.action.RectangularSelectProvider;
import org.netbeans.modules.visual.action.ObjectSceneRectangularSelectProvider;

/**
 *
 * @author Isaac
 */
public class XObjectSceneRectangularSelectProvider implements RectangularSelectProvider {

    private XObjectScene objectScene;
    private ObjectSceneRectangularSelectProvider defaultProvider;
    
    public XObjectSceneRectangularSelectProvider(XObjectScene objectScene) {
        if ( objectScene != null ) {
            this.objectScene = objectScene;
            defaultProvider = new ObjectSceneRectangularSelectProvider(objectScene);
        } else {
            throw new IllegalArgumentException( "Arguments can't be null." );
        }
    }

    public void performSelection(Rectangle sceneSelection) {
        XWidgetLocator xWidgetLocator = objectScene.getXWidgetLocator();
        if ( xWidgetLocator != null ) {
            boolean isEmptySize = sceneSelection.width == 0 || sceneSelection.height == 0;
            Rectangle newBounds = isEmptySize ? null : new Rectangle(0, 0, sceneSelection.width, sceneSelection.height);
            xWidgetLocator.locate(objectScene, sceneSelection.getLocation(), newBounds);
        } else {
            defaultProvider.performSelection(sceneSelection);
        }
    }
    
}
