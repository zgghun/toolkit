package zgg.toolkit.core.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
     * @param dateTime
     * @param pattern 默认 {@value YMDHMS}
     * @return
     */
    public static LocalDateTime parse(String dateTime, String... pattern){
        if (pattern.length == 0){
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(YMDHMS));
        }else {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern[0]));
        }
    }

    /**
     * {@link LocalDate} 解析
     * @param date
     * @param pattern 默认 {@value YMD}
     * @return
     */
    public static LocalDate parseDate(String date, String... pattern){
        if (pattern.length == 0){
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(YMD));
        }else {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern[0]));
        }
    }

    /**
     * {@link LocalTime} 解析
     * @param time
     * @param pattern 默认 {@value HMS}
     * @return
     */
    public static LocalTime parseTime(String time, String... pattern){
        if (pattern.length == 0){
            return LocalTime.parse(time, DateTimeFormatter.ofPattern(HMS));
        }else {
            return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern[0]));
        }
    }

    /**
     * {@link LocalDateTime} 格式化
     * @param dateTime
     * @param pattern 默认 {@value YMDHMS}
     * @return
     */
    public static String format(LocalDateTime dateTime, String... pattern){
        if (pattern.length == 0){
            return dateTime.format(DateTimeFormatter.ofPattern(YMDHMS));
        }else {
            return dateTime.format(DateTimeFormatter.ofPattern(pattern[0]));
        }
    }

    /**
     * {@link LocalDate} 格式化
     * @param date
     * @param pattern 默认 {@value YMD}
     * @return
     */
    public static String formatDate(LocalDate date, String... pattern){
        if (pattern.length == 0){
            return date.format(DateTimeFormatter.ofPattern(YMD));
        }else {
            return date.format(DateTimeFormatter.ofPattern(pattern[0]));
        }
    }

    /**
     * {@link LocalTime} 格式化
     * @param time
     * @param pattern 默认 {@value HMS}
     * @return
     */
    public static String formatTime(LocalTime time, String... pattern){
        if (pattern.length == 0){
            return time.format(DateTimeFormatter.ofPattern(HMS));
        }else {
            return time.format(DateTimeFormatter.ofPattern(pattern[0]));
        }
    }
}
