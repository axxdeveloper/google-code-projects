
package com.isaac.bdc.utils;

import java.util.List;
import javax.swing.JTable;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

/**
 *
 * @author Isaac
 */
public class BeansBindingHelper {

    public static void bindReadWriteTable(List javaBeans, JTable jTable, TableBindingProperty...tableBindingProperties) {
        JTableBinding tableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, javaBeans, jTable);
        for (TableBindingProperty tableBindingProperty : tableBindingProperties) {
            BeanProperty beanProperty = BeanProperty.create( tableBindingProperty.getPropertyName() );
            tableBinding.addColumnBinding(beanProperty).setColumnName( tableBindingProperty.getColumnName() );
        }
        tableBinding.bind();
    }

    public static class TableBindingProperty {

        private String propertyName = "";
        private String columnName = "";

        public TableBindingProperty() {}
        public TableBindingProperty(String propertyName, String columnName) {
            setPropertyName(propertyName);
            setColumnName(columnName);
        }

        public String getPropertyName() {
            if ( propertyName == null ) {
                propertyName = "";
            }
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public String getColumnName() {
            if ( columnName == null ) {
                columnName = "";
            }
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }
        
    }

}
