package zgg.toolkit.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;

/**
 * Created by zgg on 2018/10/26
 * 工具类
 */

public class HelpUtils {
    /**
     * 对象间属性值复制，按照属性名
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    // 获取MD5值
    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
