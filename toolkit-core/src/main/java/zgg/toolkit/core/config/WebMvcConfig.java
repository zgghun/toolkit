package zgg.toolkit.core.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigInteger;
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


    /**
     * 使用 Fastjson 提供的 FastJsonHttpMessageConverter 来替换 SpringMVC 默认的 HttpMessageConverter
     * https://github.com/alibaba/fastjson/wiki/%E5%9C%A8-Spring-%E4%B8%AD%E9%9B%86%E6%88%90-Fastjson
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        fastJsonConverter.setFastJsonConfig(this.fastJsonConfig());
        converters.add(fastJsonConverter);
    }

    private FastJsonConfig fastJsonConfig() {
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                //是否输出值为null的字段,默认为false
                SerializerFeature.WriteMapNullValue,
                //Enum输出name()或者original,默认为false
                SerializerFeature.WriteEnumUsingName,
                //结果是否格式化,默认为false（可能是处理乱码)
                SerializerFeature.PrettyFormat
        );
        // 把 null 转为空字符串
        config.setSerializeFilters(new ValueFilter() {
            @Override
            public Object process(Object object, String name, Object value) {
                if (null == value) {
                    value = "";
                }
                return value;
            }
        });
        // 解决Long转json精度丢失的问题
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        config.setSerializeConfig(serializeConfig);
        return config;
    }


}
