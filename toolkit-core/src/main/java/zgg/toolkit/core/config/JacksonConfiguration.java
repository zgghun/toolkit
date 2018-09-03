package zgg.toolkit.core.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import zgg.toolkit.core.constant.DateConsts;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by zgg on 2018/9/2
 * <p>
 * 对 jackson 的自定义配置
 * 参考 https://github.com/FasterXML/jackson-docs/wiki/JacksonHowToCustomSerializers
 **/
@Configuration
public class JacksonConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizeJackson2ObjectMapper() {
        return (Jackson2ObjectMapperBuilder builder) -> {
            // Java8 java.time 全局序列化和反序列化设置
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule
                    .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateConsts.YMDHMS)))
                    .addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DateConsts.YMD)))
                    .addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateConsts.HMS)))
                    .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateConsts.YMDHMS)))
                    .addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateConsts.YMD)))
                    .addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateConsts.HMS)))
                    .addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateConsts.HMS)));

            // 解决 Long 型数据精度丢失问题, 把过大的数转为字符串，（java中long型数据范围超出了js中数值范围原因造成的）
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance)
                    .addSerializer(Long.class, ToStringSerializer.instance)
                    .addSerializer(Long.TYPE, ToStringSerializer.instance);

            builder.modules(javaTimeModule, simpleModule);

        };
    }

}
