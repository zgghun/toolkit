package zgg.toolkit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zgg.toolkit.apitool.ToolkitApiApplication;
import zgg.toolkit.common.ToolkitCommonApplication;
import zgg.toolkit.common.rabbitmq.MQProducer;
import zgg.toolkit.system.ToolkitSystemApplication;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ToolkitApiApplication.class, ToolkitSystemApplication.class, ToolkitCommonApplication.class})
public class ToolkitApiApplicationTests {
    @Autowired
    private MQProducer mqService;


    @Test
    public void test1() {
        mqService.sendEmailToMq(Arrays.asList(111L, 222L, 333L, 444L));
        mqService.sendVerifyCodeToMq(Arrays.asList(111L, 222L, 333L, 444L));
    }

}
