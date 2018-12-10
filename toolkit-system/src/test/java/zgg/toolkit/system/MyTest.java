package zgg.toolkit.system;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import zgg.toolkit.common.utils.DateUtils;
import zgg.toolkit.common.utils.HelpUtils;
import zgg.toolkit.common.utils.JsonUtils;
import zgg.toolkit.system.constant.PerConst;
import zgg.toolkit.system.model.vo.PermissionVo;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by zgg on 2018/10/25
 */
public class MyTest {
    private static final Logger log = LoggerFactory.getLogger(MyTest.class);

    @Test
    public void test9() {
        String str = "123456";
        String s1 = HelpUtils.md5(str);
        String s2 = DigestUtils.md5DigestAsHex(str.getBytes());
        System.out.println(s1);
        System.out.println(s2.toUpperCase());
    }


    // 利用druid对数据库密码加密
    @Test
    public void test8() throws Exception {
//        ConfigTools tool = new ConfigTools();
//        String password = "222222aaaa";
//        String[] arr = tool.genKeyPair(512);
//        String privateKey = arr[0];
//        String publicKey = arr[1];
//        String encryptRassword = tool.encrypt(arr[0], password);
//        System.out.println("privateKey:" + privateKey);
//        System.out.println("publicKey:" + publicKey);
//        System.out.println("password:" + encryptRassword);
    }

    @Test
    public void test7() throws IllegalAccessException {
        Field[] fields = PerConst.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.get(this));
        }
    }

    @Test
    public void test6() {
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("a", Arrays.asList(1, 2, 3));
        System.out.println(map);
        map.get("a").add(4);
        System.out.println(map);
    }

    @Test
    public void test5() {
        PermissionVo vo = new PermissionVo();
//        vo.setId(1L);
        PermissionVo vo2 = new PermissionVo();
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

