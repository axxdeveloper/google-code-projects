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

package bdc.utils;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;

/**
 *
 * @author Isaac
 */
public class SwingHelper {

    public static void sortTable( JTable jTable ) {
        TableModel model = jTable.getModel();
        TableSorter sorter = new TableSorter(model);
        jTable.setModel(sorter);
        sorter.setTableHeader(jTable.getTableHeader());
        sorter.setSortingStatus(0, TableSorter.ASCENDING);
    }

    public static void show(JComponent c) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.getContentPane().setLayout( new BorderLayout() );
        f.getContentPane().add( c, BorderLayout.CENTER );
        f.pack();
        f.setVisible( true );
    }

}
