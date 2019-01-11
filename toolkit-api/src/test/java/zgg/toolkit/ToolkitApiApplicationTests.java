package zgg.toolkit;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zgg.toolkit.apitool.ToolkitApiApplication;
import zgg.toolkit.common.ToolkitCommonApplication;
import zgg.toolkit.common.rabbitmq.MqProducer;
import zgg.toolkit.system.ToolkitSystemApplication;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ToolkitApiApplication.class, ToolkitSystemApplication.class, ToolkitCommonApplication.class})
public class ToolkitApiApplicationTests {
    @Autowired
    private StringEncryptor encryptor;
    @Autowired
    private MqProducer mqService;



    @Test
    public void test1() {
        mqService.sendEmailToMq(Arrays.asList(111L, 222L, 333L, 444L));
        mqService.sendVerifyCodeToMq(Arrays.asList(111L, 222L, 333L, 444L));
    }

    /**
     * 敏感信息加密
     */
    @Test
    public void encryptInfo() {
        // 数据库
//        String url = encryptor.encrypt("111111");
//        String username = encryptor.encrypt("");
//        String pass = encryptor.encrypt("");
//        System.out.println(url);
//        System.out.println(username);
//        System.out.println(pass);

        // rabbit
        String host = encryptor.encrypt("");
        String username = encryptor.encrypt("");
        String password = encryptor.encrypt("");
        System.out.println(host);
        System.out.println(username);
        System.out.println(password);

        //redis
//        String url = encryptor.encrypt("");
//        String username = encryptor.encrypt("");
//        String pass = encryptor.encrypt("");
//        System.out.println(url);
//        System.out.println(username);
//        System.out.println(pass);

    }

}
