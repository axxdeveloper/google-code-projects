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
package canvas.utils;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author Isaac
 */
public class POHelper {

    public static boolean sourceChanged(Object oldValue, Object newValue) {
        return !new EqualsBuilder().append(oldValue, newValue).isEquals();
    }

    private static String convertStringToEL(String propName) {
        return "${" + propName + "}";
    }

    public static void addBinding( Object src, String srcProp, Object dst, String dstProp, Converter converter, BindingGroup bindingGroup ) {
        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, src, BeanProperty.create( srcProp ), dst, BeanProperty.create(dstProp));
        binding.setConverter( converter );
        bindingGroup.addBinding(binding);        
    }
    
    public static void addReadOnceBinding( Object src, String srcProp, Object dst, String dstProp, Converter converter, BindingGroup bindingGroup ) {
        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_ONCE, src, BeanProperty.create( srcProp ), dst, BeanProperty.create(dstProp));
        binding.setConverter( converter );
        bindingGroup.addBinding(binding);        
    }
    
    public static void addReadOnlyBinding( Object src, String srcProp, Object dst, String dstProp, Converter converter, BindingGroup bindingGroup ) {
        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, src, BeanProperty.create( srcProp ), dst, BeanProperty.create(dstProp));
        binding.setConverter( converter );
        bindingGroup.addBinding(binding);        
    }
    
    public static void addReadOnceBinding( Object src, String srcProp, Object dst, String dstProp, BindingGroup bindingGroup ) {
        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_ONCE, src, BeanProperty.create( srcProp ), dst, BeanProperty.create(dstProp));
        bindingGroup.addBinding(binding);        
    }

    public static void addReadOnlyBinding( Object src, String srcProp, Object dst, String dstProp, BindingGroup bindingGroup ) {
        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, src, BeanProperty.create( srcProp ), dst, BeanProperty.create(dstProp));
        bindingGroup.addBinding(binding);        
    }

}
