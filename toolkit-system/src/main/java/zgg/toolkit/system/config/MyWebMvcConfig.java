package zgg.toolkit.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zgg.toolkit.common.util.DateUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

/**
 * Created by zgg on 2018/08/31
 * <p>
 * WebMvcConfigurer 接口是Spring 内部的一种配置方式，采用JavaBean 的形式对框架进行个性化定制
 * <p>
 * 可配置 配置拦截器、CORS、MessageConverter 等等
 * https://docs.spring.io/spring-boot/docs/2.0.6.RELEASE/reference/htmlsingle/   搜索 WebMvcConfigurer
 */

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    /**
     * 全局格式化配置
     * 修改 java.time 的默认格式化样式,这样就不用在每个 dto 的 LocalDate 等日期类型字段上添加 @DateTimeFormat 了
     * 如果有特殊的格式,可以单独添加 @DateTimeFormat
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<LocalDateTime>() {
            @Override
            public LocalDateTime parse(String s, Locale locale) throws ParseException {
                return DateUtils.parse(s);
            }

            @Override
            public String print(LocalDateTime dateTime, Locale locale) {
                return DateUtils.format(dateTime);
            }
        });
        registry.addFormatter(new Formatter<LocalDate>() {
            @Override
            public LocalDate parse(String s, Locale locale) throws ParseException {
                return DateUtils.parseDate(s);
            }

            @Override
            public String print(LocalDate localDate, Locale locale) {
                return DateUtils.formatDate(localDate);
            }
        });
        registry.addFormatter(new Formatter<LocalTime>() {
            @Override
            public LocalTime parse(String s, Locale locale) throws ParseException {
                return DateUtils.parseTime(s);
            }

            @Override
            public String print(LocalTime localTime, Locale locale) {
                return DateUtils.formatTime(localTime);
            }
        });
    }

}
