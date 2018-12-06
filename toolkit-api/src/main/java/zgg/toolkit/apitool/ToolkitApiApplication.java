package zgg.toolkit.apitool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
        };
        new SpringApplicationBuilder(objects).web(WebApplicationType.SERVLET).run(args);
    }

    //    public static void main(String[] args) {
//
//        ConfigurableApplicationContext context = SpringApplication.run(ToolkitApiApplication.class, args);
//
//        KafkaSender sender = context.getBean(KafkaSender.class);
//
//        while (true){
//            //调用消息发送类中的消息发送方法
//            sender.send();
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
