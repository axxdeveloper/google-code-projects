
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
