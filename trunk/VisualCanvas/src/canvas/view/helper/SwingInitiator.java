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

import canvas.view.filechooser.ImageFileChangedHandler;
import canvas.view.filechooser.ImageFileFilter;
import canvas.view.locator.XCircleLabelNotationLocator;
import canvas.view.locator.XCircleTextAreaNotationLocator;
import canvas.view.locator.XPointLabelNotationLocator;
import canvas.view.locator.XPointTextAreaNotationLocator;
import canvas.view.locator.XResizableImageWidgetLocator;
import canvas.view.widget.XObjectScene;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;

/**
 *
 * @author Isaac
 */
public class SwingInitiator {

    public static JToolBar initJToolBarNotation(XObjectScene objectScene) {
        JToolBar jToolBar = new JToolBar();
        JButton jButtonLoadImage = initLoadImageButton(objectScene);
        JButton jButtonTextAreaNotation = initPointTextAreaNotationButton(objectScene);
        JButton jButtonLabelNotation = initPointLabelNotationButton(objectScene);
        JButton jButtonSaveLocalFile = initSaveImageFileButton(objectScene);
        JButton jButtonCircleLabelNotation = initCircleLabelNotation(objectScene);
        JButton jButtonCircleTextAreaNotation = initCircleTextAreaNotation(objectScene);
        jToolBar.add(jButtonSaveLocalFile);
        jToolBar.add(jButtonLoadImage);
        jToolBar.add(jButtonCircleLabelNotation);
        jToolBar.add(jButtonCircleTextAreaNotation);
        jToolBar.add(jButtonTextAreaNotation);
        jToolBar.add(jButtonLabelNotation);
        return jToolBar;
    }
    
    private static JButton initSaveImageFileButton(final XObjectScene objectScene) {
        JButton jButton = new JButton("Save Local");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser chooser = initImageFileChooser();
                    int option = chooser.showSaveDialog(null);
                    if ( JFileChooser.APPROVE_OPTION == option ) {
                        BufferedImage bimg = ImageHelper.paintXObjectScene(objectScene);
                        String selectedFile = chooser.getSelectedFile().getAbsolutePath();
                        File   outputFile   = null;
                        if ( selectedFile == null ) {
                            return;
                        }
                        if ( selectedFile.endsWith(".png") ) {
                            outputFile = new File( selectedFile );
                        } else {
                            outputFile = new File( selectedFile + ".png" );
                        }
                        ImageIO.write( bimg, "png", outputFile );
                    }
                } catch (IOException ex) {
                    throw new RuntimeException( "Save image failed." );
                }
            }
        });
        return jButton;
    }
    
    private static JFileChooser initImageFileChooser() {
        ImageFileChangedHandler selectedImageFileChangedHandler = new ImageFileChangedHandler();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setFileFilter(new ImageFileFilter());
        chooser.setAccessory(selectedImageFileChangedHandler);
        chooser.addPropertyChangeListener(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY, selectedImageFileChangedHandler);
        return chooser;
    }
    
    private static JButton initLoadImageButton(final XObjectScene objectScene) {
        JButton jButton = new JButton( "Load image" );
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                objectScene.setXWidgetLocator(new XResizableImageWidgetLocator());
            }
        });
        return jButton;
    }

    private static JButton initCircleTextAreaNotation(final XObjectScene objectScene) {
        JButton jButton = new JButton("Circle - TextArea");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                objectScene.setXWidgetLocator(new XCircleTextAreaNotationLocator());
            }
        });
        return jButton;
    }
    
    private static JButton initCircleLabelNotation(final XObjectScene objectScene) {
        JButton jButton = new JButton("Circle - Label");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                objectScene.setXWidgetLocator(new XCircleLabelNotationLocator());
            }
        });
        return jButton;
    }
    
    private static JButton initPointLabelNotationButton(final XObjectScene objectScene) {
        JButton jButton = new JButton("Point - Label");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                objectScene.setXWidgetLocator(new XPointLabelNotationLocator());
            }
        } );
        return jButton;
    }
    
    private static JButton initPointTextAreaNotationButton(final XObjectScene objectScene) {
        JButton jButton = new JButton("Point - TextArea");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                objectScene.setXWidgetLocator(new XPointTextAreaNotationLocator());
            }
        });
        return jButton;
    }

}
