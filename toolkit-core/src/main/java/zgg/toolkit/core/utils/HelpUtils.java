package zgg.toolkit.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zgg on 2018/10/26
 */

public class HelpUtils {
    public static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    // 从 commons-codec 搬的
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
            out[j++] = DIGITS_LOWER[(0xF0 & bs[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & bs[i]];
        }
        return new String(out);

    }

    public static boolean isNumeric(String str) {
        return StringUtils.isNumeric(str);
    }

    public static boolean contains(String str, String searchStr) {
        return StringUtils.contains(str, searchStr);
    }
}