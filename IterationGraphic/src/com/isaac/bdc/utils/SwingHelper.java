
package com.isaac.bdc.utils;

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
