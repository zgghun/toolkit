package zgg.toolkit.common.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by zgg on 2018/11/13
 * 参考文档 https://github.com/google/gson/blob/master/UserGuide.md
 */

public class JsonUtils {


    /**
     * 对象转 json 字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return SE_BUILDER.serializeNulls()
                .create()
                .toJson(obj);
    }


    public static final GsonBuilder SE_BUILDER = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                @Override
                public JsonElement serialize(LocalDateTime src, Type typeOfT, JsonSerializationContext context) {
                    return new JsonPrimitive(DateUtils.formatDateTime(src));
                }
            })
            .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                @Override
                public JsonElement serialize(LocalDate src, Type typeOfT, JsonSerializationContext context) {
                    return new JsonPrimitive(DateUtils.formatDate(src));
                }
            });

    public static final GsonBuilder DES_BUILDER = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                @Override
                public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return DateUtils.parseDateTime(json.getAsString());
                }
            })
            .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                @Override
                public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return DateUtils.parseDate(json.getAsString());
                }
            });
}
