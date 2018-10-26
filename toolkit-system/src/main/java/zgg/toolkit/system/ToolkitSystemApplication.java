package zgg.toolkit.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import zgg.toolkit.core.ToolkitCoreApplication;

/**
 * @author zgg
 */
@EnableTransactionManagement
@MapperScan(basePackages = "zgg.toolkit.system.mapper")
@SpringBootApplication
public class ToolkitSystemApplication {

    public static void main(String[] args) {
        Class[] objects = new Class[]{
                ToolkitCoreApplication.class,
                ToolkitSystemApplication.class};
        new SpringApplicationBuilder(objects).web(WebApplicationType.SERVLET).run(args);
    }
}
