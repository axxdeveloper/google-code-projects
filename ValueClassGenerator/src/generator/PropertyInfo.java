
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
