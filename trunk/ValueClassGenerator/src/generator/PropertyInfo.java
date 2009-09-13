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

public final class PropertyInfo {

    private final String type;
    private final String propertyName;

    private PropertyInfo(PropertyBuilder builder) {
        this.type = builder.type;
        this.propertyName = builder.propertyName;
    }

    public String getType() {
        return type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public static class PropertyBuilder {
        private String type;
        private String propertyName;

        public PropertyBuilder type(String type) {
            this.type = type;
            return this;
        }

        public PropertyBuilder propertyName(String propertyName) {
            this.propertyName = propertyName;
            return this;
        }

        public PropertyInfo build() {
            return new PropertyInfo(this);
        }

    }

}
