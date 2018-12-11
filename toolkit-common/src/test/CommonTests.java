import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zgg.toolkit.common.ToolkitCommonApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ToolkitCommonApplication.class)
public class CommonTests {
    @Value("${myconfig}")
    private String config;
    @Test
    public void test1() {
        System.out.println(config);
    }


    @Autowired
    private StringEncryptor encryptor;
    @Test
    public void encryptInfo() {
        // 数据库
        String url = encryptor.encrypt("");
        String username = encryptor.encrypt("");
        String pass = encryptor.encrypt("");
        System.out.println(url);
        System.out.println(username);
        System.out.println(pass);

        // rabbit
//        String url2 = encryptor.encrypt("");
//        String name2 = encryptor.encrypt("");
//        String pass2 = encryptor.encrypt("");
//        System.out.println(url2);
//        System.out.println(name2);
//        System.out.println(pass2);

        //redis
//        String url = encryptor.encrypt("");
//        String username = encryptor.encrypt("");
//        String pass = encryptor.encrypt("");
//        System.out.println(url);
//        System.out.println(username);
//        System.out.println(pass);

    }

}
