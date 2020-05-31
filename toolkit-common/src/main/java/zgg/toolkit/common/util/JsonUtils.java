package zgg.toolkit.common.util;

import com.google.gson.*;
import org.apache.logging.log4j.util.Strings;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by zgg on 2018/11/13
 * 参考文档 https://github.com/google/gson/blob/master/UserGuide.md
 */

public class JsonUtils {


    /**
     * 对象 -> json字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return SERIALIZE_BUILDER.serializeNulls()
                .create()
                .toJson(obj);
    }

    /**
     * json字符串 -> 对象
     *
     * @param jsonStr json字符串
     * @param tClass  返回数据类型
     * @return
     */
    public static <T> T fromJson(String jsonStr, Class<T> tClass) {
        if (!StringUtils.hasText(jsonStr)) {
            throw new RuntimeException("传入的json字符串为空");
        }
        return DESERIALIZE_BUILDER.create().fromJson(jsonStr, tClass);
    }


    /**
     * Gson序列号builder
     * 添加对 Java8 Time 的支持
     */
    private static final GsonBuilder SERIALIZE_BUILDER = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfT, context) -> new JsonPrimitive(DateUtils.formatDateTime(src)))
            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfT, context) -> new JsonPrimitive(DateUtils.formatDate(src)));

    /**
     * Gson反序列化buider
     * 添加对 Java8 Time 的支持
     */
    private static final GsonBuilder DESERIALIZE_BUILDER = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) -> DateUtils.parseDateTime(json.getAsString()))
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> DateUtils.parseDate(json.getAsString()));
}
