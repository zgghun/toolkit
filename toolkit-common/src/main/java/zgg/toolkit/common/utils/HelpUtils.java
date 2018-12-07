package zgg.toolkit.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;

/**
 * Created by zgg on 2018/10/26
 * 工具类,对第三方工具的统一引用
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
        return StringUtils.isNotBlank(str);
    }

    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    // 字符串全是数字验证
    public static boolean isNumeric(String str) {
        return StringUtils.isNumeric(str);
    }

    // 字符串查询
    public static boolean contains(String str, String searchStr) {
        return StringUtils.contains(str, searchStr);
    }

    // 获取MD5值
    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
