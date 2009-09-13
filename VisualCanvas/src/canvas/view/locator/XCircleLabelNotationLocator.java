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
package canvas.view.locator;

import canvas.view.persistence.PointPO;
import canvas.view.persistence.RectanglePO;
import canvas.view.persistence.XCircleWidgetPO;
import canvas.view.persistence.XConnectionWidgetPO;
import canvas.view.persistence.XEditableLabelWidgetPO;
import canvas.view.widget.XObjectScene;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Isaac
 */
public class XCircleLabelNotationLocator implements XWidgetLocator {

    public void locate(XObjectScene objectScene, Point location, Rectangle bounds) {
        objectScene.setXWidgetLocator(null);
        XEditableLabelWidgetPO sourceWidgetPO = new XEditableLabelWidgetPO();
        sourceWidgetPO.setLabel("Double click to edit");
        sourceWidgetPO.setPreferredLocation(new PointPO(location));
        if ( bounds != null ) {
            sourceWidgetPO.setPreferredBounds(new RectanglePO(bounds));
        }
        XCircleWidgetPO targetWidgetPO = new XCircleWidgetPO();
        targetWidgetPO.setPreferredLocation(new PointPO(location));
        XConnectionWidgetPO connWidgetPO = new XConnectionWidgetPO();
        connWidgetPO.setSourceID(sourceWidgetPO.getWidgetID());
        connWidgetPO.setTargetID(targetWidgetPO.getWidgetID());
        sourceWidgetPO.createWidget(objectScene);
        targetWidgetPO.createWidget(objectScene);
        connWidgetPO.createWidget(objectScene);        
    }

}
