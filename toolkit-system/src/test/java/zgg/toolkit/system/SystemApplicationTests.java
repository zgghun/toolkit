package zgg.toolkit.system;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zgg.toolkit.common.ToolkitCommonApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ToolkitSystemApplication.class, ToolkitCommonApplication.class})
public class SystemApplicationTests {
    @Autowired
    private StringEncryptor encryptor;

    /**
     * 利用jasypt对敏感信息加密
     */
    @Test
    public void encryptInfo() {
        // 数据库
//        String url = encryptor.encrypt("jdbc:mysql://www.hun001.top:3306/toolkit?useSSL=false");
//        String username = encryptor.encrypt("");
//        String pass = encryptor.encrypt("!");
//        System.out.println(url);
//        System.out.println(username);
//        System.out.println(pass);

        // rabbit
        String host = encryptor.encrypt("47.105.34.242");
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
