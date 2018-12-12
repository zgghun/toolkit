package zgg.toolkit.common.utils;

import org.springframework.util.StringUtils;
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

    public static boolean isNotBlank(String str) {
        return StringUtils.hasText(str);
    }

    public static boolean isBlank(String str) {
        return !StringUtils.hasText(str);
    }

    // 字符串是否全是数字
    public static boolean isAllNumber(String str) {
        if (isBlank(str)){
            return false;
        }
        final int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // 获取MD5值
    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
