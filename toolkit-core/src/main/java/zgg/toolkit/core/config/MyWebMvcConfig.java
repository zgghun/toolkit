package zgg.toolkit.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zgg.toolkit.core.utils.DateUtils;

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
 */

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    // 自定义java8 time 全局格式化
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

//    // 全局跨域配置 https://docs.spring.io/spring/docs/5.0.10.RELEASE/spring-framework-reference/web.html#mvc-cors
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")
//                .allowedOrigins("http://domain2.com")
//                .allowedMethods("PUT", "DELETE")
//                .allowedHeaders("header1", "header2", "header3")
//                .exposedHeaders("header1", "header2")
//                // 允许跨域传递凭证，如传cookie, 用于解决跨域传递shiro的sessionId
//                .allowCredentials(true)
//                .maxAge(3600);
//                /*
//                    jquery的ajax的post方法请求：
//                        $.ajax({
//                                type: "POST",
//                                url: "http://xxx.com/api/test",
//                                dataType: 'jsonp',
//                                xhrFields: {
//                                withCredentials: true
//                            },
//                            crossDomain: true,
//                            success:function(){
//                            },
//                            error:function(){
//                            }
//                        })
//                */
//    }

}
