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
import java.util.Collections;
import java.util.List;

public final class ClassInfo {

    private final String name;
    private final List<PropertyInfo> properties;

    private ClassInfo(ClassBuilder builder) {
        this.name = builder.name;
        this.properties = builder.properties;
    }

    public String getName() {
        return name;
    }

    public List<PropertyInfo> getProperties() {
        return Collections.unmodifiableList(new ArrayList<PropertyInfo>(properties));
    }

    public static class ClassBuilder {
        private String name;
        private List<PropertyInfo> properties;
        
        public ClassBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        public ClassBuilder properties(List<PropertyInfo> properties) {
            this.properties = properties;
            return this;
        }

        public ClassInfo build() {
            return new ClassInfo(this);
        }

    }

}
