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

import canvas.view.widget.XWidget;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Isaac
 */
public class CommonPopupMenuProvider implements PopupMenuProvider {

    public JPopupMenu getPopupMenu(final Widget source, Point localLocation) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ObjectScene scene = (ObjectScene) source.getScene();
                List<Widget> widgets = scene.findWidgets(scene.findObject(source));
                scene.removeObject( scene.findObject(source) );
                if ( widgets != null ) {
                    for (Widget widget : widgets) {
                        widget.removeFromParent();
                    }
                }
            }
        });
        JMenuItem bringToBackItem = new JMenuItem("Bring to back");
        bringToBackItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                source.bringToBack();
            }
        });
        JMenuItem colorItem = new JMenuItem("Change color");
        colorItem.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ( source instanceof XWidget ) {
                    XWidget xSource = (XWidget) source;
                    Color colorToChange = JColorChooser.showDialog((Component)e.getSource(), "Change color", xSource.currentColor());
                    xSource.changeColor( colorToChange );
                }
            }
        } );
        menu.add(deleteItem);
        menu.add(bringToBackItem);
        menu.add(colorItem);
        return menu;
    }
}
