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

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Isaac
 */
public class XTextAreaWidgetDocumentHandler implements DocumentListener {

    private Widget descWidget;
    private ObjectScene objectScene;
    
    public XTextAreaWidgetDocumentHandler(ObjectScene objectScene, Widget descWidget) {
        if ( objectScene != null && descWidget != null ) {
            this.objectScene = objectScene;
            this.descWidget = descWidget;            
        } else {
            throw new RuntimeException( "Class member can't be null." );
        }
    }
    
    public void insertUpdate(DocumentEvent e) {
        descWidget.revalidate();
        objectScene.validate();
    }
    public void removeUpdate(DocumentEvent e) {
        descWidget.revalidate();
        objectScene.validate();
    }
    public void changedUpdate(DocumentEvent e) {
        descWidget.revalidate();
        objectScene.validate();
    }
    
}
