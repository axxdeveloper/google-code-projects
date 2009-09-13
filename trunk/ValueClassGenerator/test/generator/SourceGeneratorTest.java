
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
