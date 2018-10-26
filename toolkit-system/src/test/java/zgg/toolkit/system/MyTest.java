package zgg.toolkit.system;

import org.junit.Test;

import java.util.Base64;

/**
 * Created by zgg on 2018/10/25
 */
public class MyTest {

    @Test
    public void test1(){
        String str = "aaaaaaa";
        byte[] b = str.getBytes();
        System.out.println(b);
        String base64 = Base64.getEncoder().encodeToString(str.getBytes());
        System.out.println(base64);
    }
}

