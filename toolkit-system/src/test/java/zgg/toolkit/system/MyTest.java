package zgg.toolkit.system;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zgg.toolkit.core.utils.DateUtils;
import zgg.toolkit.core.utils.HelpUtils;
import zgg.toolkit.core.utils.JsonUtils;
import zgg.toolkit.system.model.vo.PermissionVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * Created by zgg on 2018/10/25
 */
public class MyTest {
    private static final Logger log = LoggerFactory.getLogger(MyTest.class);
    @Test
    public void test5() {
        PermissionVO vo = new PermissionVO();
        vo.setId(1L);
        PermissionVO vo2 = new PermissionVO();
        vo.setChildren(Arrays.asList(vo2));
        System.out.println(JsonUtils.toJson(vo));
    }


    @Test
    public void test4() {
        String dateTime = "2018-10-11 10:23:00";
        log.info(DateUtils.parse(dateTime).toString());

        LocalDateTime dateTime1 = LocalDateTime.now();
        log.info(DateUtils.format(dateTime1));
        log.info(DateUtils.format(dateTime1, "yyyy年MM月dd HH:mm:ss"));
    }


    @Test
    public void test3() {
        String str = "123456";
        String str2 = HelpUtils.md5(str);
        System.out.println(str2);
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
    public void test1() {
        String str = "aaaaaaa";
        byte[] b = str.getBytes();
        System.out.println(b);
        String base64 = Base64.getEncoder().encodeToString(str.getBytes());
        System.out.println(base64);
    }
}

