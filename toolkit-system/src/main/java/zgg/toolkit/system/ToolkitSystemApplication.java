package zgg.toolkit.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import zgg.toolkit.core.ToolkitCoreApplication;

/**
 * @author zgg
 */
@MapperScan(basePackages = "zgg.toolkit.system.mapper")
@SpringBootApplication
public class ToolkitSystemApplication {

    public static void main(String[] args) {
        Class[] objects = new Class[]{
                ToolkitCoreApplication.class,
//                ToolkitApiApplication.class,
                ToolkitSystemApplication.class};
        new SpringApplicationBuilder(objects).web(WebApplicationType.SERVLET).run(args);
    }
}
