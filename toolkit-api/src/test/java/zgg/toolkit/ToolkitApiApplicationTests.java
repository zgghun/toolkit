package zgg.toolkit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zgg.toolkit.apitool.ToolkitApiApplication;
import zgg.toolkit.common.rabbitmq.RabbitMqConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ToolkitApiApplication.class)
public class ToolkitApiApplicationTests {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void contextLoads() {
        String msg = "hello word!" +
                "你好，世界！";
        System.out.println("发送：" + msg);
        for (int i = 0; i < 1000; i++) {
            System.out.println("#####发送次数：" + i);
//            mqTemplate.convertAndSend(RabbitMqConfig.EMAIL_QUEUE, msg + "direct");
            amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "email", msg + "topic, email");
            amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "verifyCode", msg + "topic, verifyCode");
            amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "abc", msg + "topic, test");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
