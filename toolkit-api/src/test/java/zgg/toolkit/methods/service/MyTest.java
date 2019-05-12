package zgg.toolkit.methods.service;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

/**
 * Created by zgg on 2018/11/20
 */

public class MyTest {
    @Test
    public void test8() throws Exception {
        ConfigTools tool = new ConfigTools();
        String password = "K8c6E62eCDfc8BE6!";
        String[] arr = tool.genKeyPair(512);
        String privateKey = arr[0];
        String publicKey = arr[1];
        String encryptRassword = tool.encrypt(arr[0], password);
        System.out.println("privateKey:" + privateKey);
        System.out.println("publicKey:" + publicKey);
        System.out.println("password:" + encryptRassword);
    }

    @Test
    public void test1() {

    }

}
