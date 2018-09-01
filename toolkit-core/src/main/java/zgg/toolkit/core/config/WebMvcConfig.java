package zgg.toolkit.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by zgg on 2018/08/31
 * <p>
 * WebMvcConfigurer 接口是Spring 内部的一种配置方式，采用JavaBean 的形式对框架进行个性化定制
 * <p>
 * 可配置 配置拦截器、CORS、MessageConverter 等等
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

}
