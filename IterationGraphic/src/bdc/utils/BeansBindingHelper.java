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
