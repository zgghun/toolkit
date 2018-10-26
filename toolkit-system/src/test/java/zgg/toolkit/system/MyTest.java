package zgg.toolkit.system;

import org.junit.Test;
import zgg.toolkit.core.utils.HelpUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by zgg on 2018/10/25
 */
public class MyTest {
    @Test
    public void test3() {
        String str = "123456";
        String str2 = HelpUtils.md5(str);
        System.out.println(str2);
        System.out.println("e10adc3949ba59abbe56e057f20f883e");
    }

    @Test
    public void test2() {
        List list1 = null;
        List list2 = new ArrayList();
        System.out.println(list1 == null);
        System.out.println(list1.size() == 1);
        System.out.println(list2 == null);
        System.out.println(list2.size() == 1);
    }


    @Test
    public void test1(){
        String str = "aaaaaaa";
        byte[] b = str.getBytes();
        System.out.println(b);
        String base64 = Base64.getEncoder().encodeToString(str.getBytes());
        System.out.println(base64);
    }
}

