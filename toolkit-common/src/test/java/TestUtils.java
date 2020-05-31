import org.junit.Test;
import zgg.toolkit.common.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zgg
 * @date 2020-05-31
 */
public class TestUtils {
    @Test
    public void testJsonUtils() {
        Map<String, Object> map = new HashMap<>();
        map.put("111", "aaa");
        map.put("bbb", "");
        map.put("ccc", "null");
        map.put("222", null);
        String json = JsonUtils.toJson(map);
        System.out.println(json);

        Map map1 = JsonUtils.fromJson(json, Map.class);
        System.out.println(map1);
    }
}
