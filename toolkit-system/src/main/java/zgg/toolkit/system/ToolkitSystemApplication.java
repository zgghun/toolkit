package zgg.toolkit.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import zgg.toolkit.common.ToolkitCommonApplication;

/**
 * @author zgg
 */
@EnableTransactionManagement
@MapperScan(basePackages = {"zgg.toolkit.system.mapper"})
@SpringBootApplication
public class ToolkitSystemApplication {

    public static void main(String[] args) {
        Class[] classes = new Class[]{
                ToolkitSystemApplication.class,
                ToolkitCommonApplication.class
        };
        new SpringApplicationBuilder(classes).web(WebApplicationType.SERVLET).run(args);
    }
}
