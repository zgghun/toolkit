package zgg.toolkit.apitool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import zgg.toolkit.apitool.kafka.KafkaSender;

/**
 * @author zgg
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("zgg.toolkit.apitool.mapper")
public class ToolkitApiApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(ToolkitApiApplication.class, args);

        KafkaSender sender = context.getBean(KafkaSender.class);

        while (true){
            //调用消息发送类中的消息发送方法
            sender.send();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
//    public static void main(String[] args) {
//        Class[] objects = new Class[]{
//                ToolkitCoreApplication.class,
//                ToolkitSystemApplication.class,
//                ToolkitApiApplication.class
//        };
//        new SpringApplicationBuilder(objects).web(WebApplicationType.SERVLET).run(args);
//    }
}
