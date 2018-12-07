package zgg.toolkit.apitool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import zgg.toolkit.common.ToolkitCommonApplication;
import zgg.toolkit.system.ToolkitSystemApplication;

/**
 * @author zgg
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("zgg.toolkit.apitool.mapper")
public class ToolkitApiApplication {
    public static void main(String[] args) {
        Class[] objects = new Class[]{
                ToolkitApiApplication.class,
                ToolkitSystemApplication.class,
                ToolkitCommonApplication.class
        };
        new SpringApplicationBuilder(objects).web(WebApplicationType.SERVLET).run(args);
    }
}
