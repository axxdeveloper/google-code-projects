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

public class SourceGenerator {

    private static final String LINE_SEP = System.getProperty("line.separator");
    private static final String BUILDER_SUFFIX = "Builder";
    /** four empty space */
    private static final String INDENTATION = "    ";

    

    public static String generate(ClassInfo cls) {
        StringBuilder src = new StringBuilder();

        src.append(indentation(0) + "public class " + cls.getName() + " { " + LINE_SEP );
        // declare properties
        src.append(LINE_SEP);
        for (PropertyInfo propertyInfo : cls.getProperties()) {
            src.append(indentation(1) + "private final " + propertyInfo.getType() + " " + propertyInfo.getPropertyName() + ";" + LINE_SEP);
        }
        // constructor
        src.append( LINE_SEP );
        src.append( indentation(1) + "private " + cls.getName() + "(" + cls.getName() + BUILDER_SUFFIX + " builder) {" + LINE_SEP );
        for (PropertyInfo propertyInfo : cls.getProperties()) {
            src.append( indentation(2) + "this." + propertyInfo.getPropertyName() + " = builder." + propertyInfo.getPropertyName() + ";" + LINE_SEP );
        }
        src.append( indentation(1) + "}" + LINE_SEP );

        // getters
        for (PropertyInfo propertyInfo : cls.getProperties()) {
            src.append( LINE_SEP );
            src.append(indentation(1) + "public " + propertyInfo.getType() + " " + getterString(propertyInfo.getPropertyName()) + "() {" + LINE_SEP );
            src.append(indentation(2) + "return " + propertyInfo.getPropertyName() + ";" + LINE_SEP);
            src.append(indentation(1) + "}" + LINE_SEP );
        }

        // builder
        src.append( LINE_SEP );
        src.append( indentation(1) + "public static class " + cls.getName() + BUILDER_SUFFIX + " {" + LINE_SEP );

        // declare properties
        src.append(LINE_SEP);
        for (PropertyInfo propertyInfo : cls.getProperties()) {
            src.append(indentation(2) + "private " + propertyInfo.getType() + " " + propertyInfo.getPropertyName() + ";" + LINE_SEP);
        }
        
        // setters
        for (PropertyInfo propertyInfo : cls.getProperties()) {
            src.append(LINE_SEP);
            src.append(indentation(2) + "public " + cls.getName() + BUILDER_SUFFIX + " " + propertyInfo.getPropertyName() + "(" + propertyInfo.getType() + " " + propertyInfo.getPropertyName() + ") {" + LINE_SEP );
            src.append(indentation(3) + "this." + propertyInfo.getPropertyName() + " = " + propertyInfo.getPropertyName() + ";" + LINE_SEP);
            src.append(indentation(3) + "return this;" + LINE_SEP);
            src.append(indentation(2) + "}" + LINE_SEP );
        }

        // builder
        src.append(LINE_SEP);
        src.append(indentation(2) + "public " + cls.getName() + " build() {" + LINE_SEP);
        src.append(indentation(3) + "return new " + cls.getName() + "(this);" + LINE_SEP);
        src.append(indentation(2) + "}" + LINE_SEP);
        src.append(LINE_SEP);
        src.append(indentation(1) + "}" + LINE_SEP);
        src.append(LINE_SEP);
        src.append(indentation(0) + "}" + LINE_SEP);
        return src.substring(0);
    }

    private static String getterString(String propertyName) {
        String firstText = propertyName.substring(0, 1);
        return "get" + firstText.toUpperCase() + propertyName.substring(1);
    }

    private static String indentation(int times) {
        StringBuilder result = new StringBuilder();
        for ( int i = 0; i < times; i++ ) {
            result.append( INDENTATION );
        }
        return result.substring(0);
    }

}
