package zgg.toolkit.core.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by zgg on 2018/08/31
 * <p>
 * WebMvcConfigurer 接口是Spring 内部的一种配置方式，采用JavaBean 的形式对框架进行个性化定制
 * <p>
 * 可配置 配置拦截器、CORS、MessageConverter 等等
 */

//@Configuration
//@EnableWebMvc
public class MyWebMvcConfig implements WebMvcConfigurer {

    // 全局跨域配置 https://docs.spring.io/spring/docs/5.0.10.RELEASE/spring-framework-reference/web.html#mvc-cors
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://domain2.com")
                .allowedMethods("PUT", "DELETE")
                .allowedHeaders("header1", "header2", "header3")
                .exposedHeaders("header1", "header2")
                // 允许跨域传cookie
                .allowCredentials(true).maxAge(3600);

                /*
                    jquery的ajax的post方法请求：
                        $.ajax({
                                type: "POST",
                                url: "http://xxx.com/api/test",
                                dataType: 'jsonp',
                                xhrFields: {
                                withCredentials: true
                            },
                            crossDomain: true,
                            success:function(){
                            },
                            error:function(){
                            }
                        })
                */

    }
}
