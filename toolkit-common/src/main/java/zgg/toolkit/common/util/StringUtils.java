package zgg.toolkit.common.util;

/**
 * Created by zgg on 2019/04/28
 */

public class StringUtils {

    /**
     * 判断字符串是否全是数字
     *
     * @param str
     * @return
     */
    public static boolean isAllNumber(String str) {
        if (!StringUtils.hasText(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断 {@code String} 是否有实际的文本值<br>
     * 参考 {@link org.springframework.util.StringUtils#hasText(String)}
     *
     * @param str 输入的字符串
     * @return 如果 {@code String} 不为null，且长度大于0，且包含非空白字符则返回 {@code true}
     */
    public static boolean hasText(String str) {
        return str != null && !str.isEmpty() && containsText(str);
    }

    private static boolean containsText(String str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private StringUtils() {
    }
}
