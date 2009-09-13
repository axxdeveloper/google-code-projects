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

package canvas.view.widget;

import canvas.view.locator.XWidgetLocator;
import canvas.view.materials.ComponentWidgetStateChangedHandler;
import canvas.view.materials.SelectionChangeHandler;
import canvas.view.materials.XObjectSceneRectangularSelectProvider;
import canvas.view.persistence.XObjectScenePO;
import canvas.view.persistence.XWidgetPO;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.model.ObjectSceneEventType;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Administrator
 */
public class XObjectScene extends ObjectScene {

    private XWidgetLocator xWidgetLocator;
    private LayerWidget bgLayer;
    private LayerWidget mainLayer;
    private LayerWidget connLayer;
    
    private XObjectScene() {
        super();
        init();
    }
    
    private void init() {
        bgLayer = new LayerWidget(this);
        mainLayer = new LayerWidget(this);
        connLayer = new LayerWidget(this);
        addChild(bgLayer);
        addChild(mainLayer);
        addChild(connLayer);
        getActions().addAction(ActionFactory.createRectangularSelectAction( ActionFactory.createDefaultRectangularSelectDecorator (this), bgLayer, new XObjectSceneRectangularSelectProvider(this)));
        addObjectSceneListener(new SelectionChangeHandler(), ObjectSceneEventType.OBJECT_SELECTION_CHANGED);
        addObjectSceneListener(new ComponentWidgetStateChangedHandler(), ObjectSceneEventType.OBJECT_STATE_CHANGED);
    }
    
    public static XObjectScene newInstance(XObjectScenePO objectScenePO) {
        XObjectScene objectScene = new XObjectScene();
        for (XWidgetPO xWidgetPO : objectScenePO.getChildren()) {
            xWidgetPO.createWidget( objectScene );
        }
        return objectScene;
    }

    public XObjectScenePO complete() {
        validate();
        XObjectScenePO result = new XObjectScenePO();
        for (Widget child : getMainLayer().getChildren()) {
            result.addChild(((XWidget) child).complete());
        }
        for (Widget child : getConnLayer().getChildren()) {
            result.addChild(((XWidget) child).complete());
        }
        return result;
    }
    
    public LayerWidget getBgLayer() {
        return bgLayer;
    }

    public LayerWidget getMainLayer() {
        return mainLayer;
    }

    public LayerWidget getConnLayer() {
        return connLayer;
    }

    /**
     * @see XWidgetLocator
     */
    public XWidgetLocator getXWidgetLocator() {
        return xWidgetLocator;
    }

    public void setXWidgetLocator(XWidgetLocator xWidgetLocator) {
        this.xWidgetLocator = xWidgetLocator;
    }
    
}
