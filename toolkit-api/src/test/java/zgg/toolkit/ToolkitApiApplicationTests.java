package zgg.toolkit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zgg.toolkit.apitool.ToolkitApiApplication;
import zgg.toolkit.common.ToolkitCommonApplication;
import zgg.toolkit.common.rabbitmq.MqConst;
import zgg.toolkit.common.utils.DateUtils;
import zgg.toolkit.system.ToolkitSystemApplication;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ToolkitApiApplication.class, ToolkitSystemApplication.class, ToolkitCommonApplication.class})
public class ToolkitApiApplicationTests {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void contextLoads() {
        String msg = "hello word! 你好，世界！";
        System.out.println("发送：" + msg);
        for (int i = 0; i < 1000; i++) {
            System.out.println("#####发送次数：" + i);
            amqpTemplate.convertAndSend(MqConst.DIRECT_EXCHANGE, MqConst.EMAIL_QUEUE, msg + DateUtils.format(LocalDateTime.now()));
            amqpTemplate.convertAndSend(MqConst.DIRECT_EXCHANGE, MqConst.VERIFY_CODE_QUEUE, msg + DateUtils.format(LocalDateTime.now()));
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
