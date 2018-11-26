package zgg.toolkit.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zgg on 2018/10/26
 * 工具类,对第三方工具的统一引用
 */

public class HelpUtils {
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

    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    // 获取MD5值 ，从 commons-codec 复制的
    public static String md5(String str) {
        byte[] bs = new byte[]{};
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            bs = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        final int l = bs.length;
        final char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_UPPER[(0xF0 & bs[i]) >>> 4];
            out[j++] = DIGITS_UPPER[0x0F & bs[i]];
        }
        return new String(out);
    }
}
