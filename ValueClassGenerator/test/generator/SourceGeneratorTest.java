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
package generator;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class SourceGeneratorTest {

    @Test
    public void testBuild() {
        ClassInfo.ClassBuilder clsBuilder = new ClassInfo.ClassBuilder();
        List<PropertyInfo> properties = new ArrayList<PropertyInfo>();
        PropertyInfo.PropertyBuilder propertyBuilder = new PropertyInfo.PropertyBuilder();
        properties.add( propertyBuilder.type("String").propertyName("name").build() );
        properties.add( propertyBuilder.type("List<PropertyInfo>").propertyName("properties").build() );
        ClassInfo clsInfo = clsBuilder.name("ClassInfo").properties( properties ).build();
        System.out.println(SourceGenerator.generate(clsInfo));
    }


}
