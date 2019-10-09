package zgg.toolkit.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by zgg on 2018/10/25
 * 时间工具类，主要处理 java8 的 time
 */

public class DateUtils {
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD = "yyyy-MM-dd";
    public static final String HMS = "HH:mm:ss";

    /**
     * {@link LocalDateTime} 解析
     *
     * @param dateTime 日期时间字符串
     * @param pattern  默认 {@value YMDHMS}
     * @return LocalDateTime实例
     * @throws DateTimeParseException if the text cannot be parsed
     */
    public static LocalDateTime parseDateTime(String dateTime, String... pattern) {
        if (pattern.length == 0) {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(YMDHMS));
        } else {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern[0]));
        }
    }

    /**
     * {@link LocalDate} 解析
     *
     * @param localDate 日期字符串
     * @param pattern   默认解析格式 {@value YMD}
     * @return
     */
    public static LocalDate parseDate(String localDate, String... pattern) {
        if (pattern.length == 0) {
            return LocalDate.parse(localDate, DateTimeFormatter.ofPattern(YMD));
        } else {
            return LocalDate.parse(localDate, DateTimeFormatter.ofPattern(pattern[0]));
        }
    }

    /**
     * {@link LocalTime} 解析
     *
     * @param localTime 时间字符串
     * @param pattern   默认解析格式 {@value HMS}
     * @return
     */
    public static LocalTime parseTime(String localTime, String... pattern) {
        if (pattern.length == 0) {
            return LocalTime.parse(localTime, DateTimeFormatter.ofPattern(HMS));
        } else {
            return LocalTime.parse(localTime, DateTimeFormatter.ofPattern(pattern[0]));
        }
    }

    /**
     * {@link LocalDateTime} 格式化
     *
     * @param dateTime LocalDateTime
     * @param pattern  默认解析格式 {@value YMDHMS}
     * @return 格式化的日期时间字符串
     */
    public static String formatDateTime(LocalDateTime dateTime, String... pattern) {
        if (pattern.length == 0) {
            return dateTime.format(DateTimeFormatter.ofPattern(YMDHMS));
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern(pattern[0]));
        }
    }

    /**
     * {@link LocalDate} 格式化
     *
     * @param localDate LocalDate
     * @param pattern   默认 {@value YMD}
     * @return 格式化的日期字符串
     */
    public static String formatDate(LocalDate localDate, String... pattern) {
        if (pattern.length == 0) {
            return localDate.format(DateTimeFormatter.ofPattern(YMD));
        } else {
            return localDate.format(DateTimeFormatter.ofPattern(pattern[0]));
        }
    }

    /**
     * {@link LocalTime} 格式化
     *
     * @param localTime LocalTime
     * @param pattern   默认 {@value HMS}
     * @return 格式化的时间字符串
     */
    public static String formatTime(LocalTime localTime, String... pattern) {
        if (pattern.length == 0) {
            return localTime.format(DateTimeFormatter.ofPattern(HMS));
        } else {
            return localTime.format(DateTimeFormatter.ofPattern(pattern[0]));
        }
    }
}
