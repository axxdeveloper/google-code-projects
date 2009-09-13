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
package canvas.view.helper;

import canvas.view.materials.CommonPopupMenuProvider;
import canvas.view.widget.XObjectScene;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Isaac
 */
public class ActionInitiator {

    public static void initDefaultAction(XObjectScene objectScene, Widget...widgets) {
        LayerWidget mainLayer = objectScene.getMainLayer();
        LayerWidget connLayer = objectScene.getConnLayer();
        for (Widget widget : widgets) {
            widget.getActions().addAction( objectScene.createSelectAction() );
            widget.getActions().addAction( objectScene.createObjectHoverAction() );
            widget.getActions().addAction( ActionFactory.createPopupMenuAction(new CommonPopupMenuProvider()) );
            widget.getActions().addAction( ActionFactory.createAlignWithMoveAction(mainLayer, connLayer, null) );
            widget.getActions().addAction( 0, ActionFactory.createAlignWithResizeAction(mainLayer, connLayer, null) );
        }
    }
    
    public static void initNotationWidgetAction(XObjectScene objectScene, Widget targetWidget, Widget sourceWidget, Widget connWidget) {
        LayerWidget mainLayer = objectScene.getMainLayer();
        LayerWidget connLayer = objectScene.getConnLayer();
        targetWidget.getActions().addAction( objectScene.createSelectAction() );
        sourceWidget.getActions().addAction( objectScene.createSelectAction() );
        targetWidget.getActions().addAction( objectScene.createObjectHoverAction() );
        sourceWidget.getActions().addAction( objectScene.createObjectHoverAction() );
        targetWidget.getActions().addAction( ActionFactory.createPopupMenuAction(new CommonPopupMenuProvider()) );
        sourceWidget.getActions().addAction( ActionFactory.createPopupMenuAction(new CommonPopupMenuProvider()) );
        connWidget  .getActions().addAction( ActionFactory.createPopupMenuAction(new CommonPopupMenuProvider()) );
        targetWidget.getActions().addAction( ActionFactory.createAlignWithMoveAction(mainLayer, connLayer, null) );
        sourceWidget.getActions().addAction( ActionFactory.createAlignWithMoveAction(mainLayer, connLayer, null) );
        targetWidget.getActions().addAction( 0, ActionFactory.createAlignWithResizeAction(mainLayer, connLayer, null) );
        sourceWidget.getActions().addAction( 0, ActionFactory.createAlignWithResizeAction(mainLayer, connLayer, null) );
    }
    
}
