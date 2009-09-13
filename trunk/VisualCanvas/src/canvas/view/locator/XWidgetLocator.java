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

import canvas.view.widget.XObjectScene;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Used to perform actions in XObjectScene with given information.<br/>
 * For example, if you want to locate a XTextAreaWidget in XObjectScene, <br/>
 * you can call <code> XObjectScene.setXWidgetLocator(new XPointTextAreaWidgetNotationLocator());</code><br/>
 * XPointTextAreaWidgetNotationLocator.locate implements how to locate these widgets.
 * 
 * @param objectScene target objectScene
 * @param location where to perform
 * @param bounds how big this perform
 * @author Isaac
 */
public interface XWidgetLocator {
    void locate(XObjectScene objectScene, Point location, Rectangle bounds);
}
