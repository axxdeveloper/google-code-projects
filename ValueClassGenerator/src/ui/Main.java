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
package ui;

import generator.ClassInfo;
import generator.PropertyInfo;
import generator.SourceGenerator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.miginfocom.swing.MigLayout;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    private void start() {
        JPanel main = new JPanel(new MigLayout());
        JTextArea srcTextArea = new JTextArea(20,80);
        JTextField classField = new JTextField(20);
        DefaultTableModel propertyModel = defaultTableModel();
        main.add( createButtons(srcTextArea, classField, propertyModel), "wrap" );
        main.add( createClassNamePanel(classField), "wrap" );
        main.add( createPropertiesPanel(propertyModel), "wrap" );
        main.add( createSourceCodeTextArea(srcTextArea), "center" );
        show(main);
    }

    private JPanel createButtons( JTextArea srcTextArea, JTextField classField, DefaultTableModel propertyModel ) {
        JPanel result = new JPanel( new FlowLayout() );
        result.add( createAddRowBtn(propertyModel) );
        result.add( createGenerateBtn(propertyModel, srcTextArea, classField) );
        return result;
    }

    private JPanel createClassNamePanel(JTextField classField) {
        JPanel result = new JPanel( new FlowLayout() );
        result.add( new JLabel("class name ") );
        result.add( classField );
        return result;
    }

    private JPanel createSourceCodeTextArea(JTextArea srcTextArea) {
        JPanel jPanel = new JPanel( new BorderLayout() );
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(srcTextArea);
        jPanel.add( pane, BorderLayout.CENTER );
        return jPanel;
    }

    private JPanel createPropertiesPanel(DefaultTableModel propertyModel) {
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(createPropertyTable(propertyModel), BorderLayout.CENTER);
        return jPanel;
    }

    private JPanel createPropertyTable(TableModel propertyModel) {
        JPanel jPanel = new JPanel(new BorderLayout());
        JScrollPane pane = new JScrollPane();
        pane.setPreferredSize( new Dimension(600, 120) );
        final JTable jTable = new JTable();
        jTable.setModel( propertyModel );
        pane.setViewportView( jTable );
        jPanel.add( pane, BorderLayout.CENTER );
        return jPanel;
    }

    private DefaultTableModel defaultTableModel() {
        return new DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "type", "propertyName"
            }
        );
    }

    private JButton createGenerateBtn(final DefaultTableModel propertyModel, final JTextArea srcTextArea, final JTextField classField) {
        JButton result = new JButton("generate");
        result.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                List<PropertyInfo> properties = new ArrayList<PropertyInfo>();
                PropertyInfo.PropertyBuilder propBuilder = new PropertyInfo.PropertyBuilder();
                for (Object object : propertyModel.getDataVector()) {
                    Vector data = (Vector) object;
                    String type = (String) data.get(0);
                    String propertyName = (String) data.get(1);
                    if ( !isBlank(type) && !isBlank(propertyName) ) {
                        properties.add( propBuilder.type(type).propertyName(propertyName).build() );
                    }
                }
                ClassInfo clsInfo = new ClassInfo.ClassBuilder()
                        .name( classField.getText() ).properties(properties).build();
                srcTextArea.setText( SourceGenerator.generate(clsInfo) );
            }

        });
        return result;
    }

    private boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    private JButton createAddRowBtn(final DefaultTableModel propertyModel) {
        JButton addRowBtn = new JButton("add row");
        addRowBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                propertyModel.addRow( new String[]{null, null} );
            }

        });
        return addRowBtn;
    }

    private void show(JComponent jComp) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.getContentPane().add(jComp);
        f.pack();
        f.setVisible(true);
    }

}
