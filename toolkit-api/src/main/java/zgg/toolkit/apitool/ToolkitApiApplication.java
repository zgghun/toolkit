package zgg.toolkit.apitool;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import zgg.toolkit.core.ToolkitCoreApplication;
import zgg.toolkit.system.ToolkitSystemApplication;

/**
 * @author zgg
 */
@SpringBootApplication
public class ToolkitApiApplication {

    public static void main(String[] args) {
        Class[] objects = new Class[]{
                ToolkitCoreApplication.class,
                ToolkitSystemApplication.class,
                ToolkitApiApplication.class
        };
        new SpringApplicationBuilder(objects).web(WebApplicationType.SERVLET).run(args);
    }
}
