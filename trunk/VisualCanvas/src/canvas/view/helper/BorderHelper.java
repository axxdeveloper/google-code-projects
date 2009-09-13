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

import java.awt.Color;
import org.netbeans.api.visual.border.Border;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.modules.visual.border.BevelBorder;
import org.netbeans.modules.visual.border.EmptyBorder;
import org.netbeans.modules.visual.border.ResizeBorder;

/**
 *
 * @author Isaac
 */
public class BorderHelper {
    
    public enum BORDER_TYPE { BEVEL, EMPTY, RESIZE };
    
    public static BORDER_TYPE chooseBorderType(Border border) {
        if ( border instanceof BevelBorder ) {
            return BORDER_TYPE.BEVEL;
        } else if ( border instanceof ResizeBorder ) {
            return BORDER_TYPE.RESIZE;
        } else if ( border instanceof EmptyBorder ) {
            return BORDER_TYPE.EMPTY;
        } else {
            return BORDER_TYPE.EMPTY;
        }
    }
    
    public static Border chooseBorder(BORDER_TYPE borderType) {
        if ( BORDER_TYPE.BEVEL == borderType ) {
            return BorderFactory.createBevelBorder( true );
        } else if ( BORDER_TYPE.EMPTY == borderType ) {
            return BorderFactory.createEmptyBorder();
        } else if ( BORDER_TYPE.RESIZE == borderType ) {
            return BorderFactory.createResizeBorder(10, Color.BLUE, false);
        } else {
            return BorderFactory.createEmptyBorder();
        }
    }
    
}
