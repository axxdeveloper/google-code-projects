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

import canvas.view.persistence.XWidgetPO;
import java.awt.Color;
import java.util.UUID;

/**
 *
 * @author Isaac
 */
public interface XWidget {

    void setWidgetID(UUID widgetID);
    UUID getWidgetID();
    
    /**
     * Used to collect widget's data.
     * @return widget's data
     */
    XWidgetPO complete();
    
    /**
     * Used to change widget's border color. <br/>
     * Every widget can define how to change color itself.
     * @param color what color to change
     */
    void changeColor(Color color);
    
    /**
     * What color before calling changeColor?
     * @return current color
     */
    Color currentColor();
}
