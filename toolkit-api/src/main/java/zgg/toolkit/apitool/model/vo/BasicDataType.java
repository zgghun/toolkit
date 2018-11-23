package zgg.toolkit.apitool.model.vo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zgg on 2018/11/23
 */

public class BasicDataType {
    public static boolean contains(Class clazz) {
        List<Class> list = Arrays.asList(
                Boolean.class,
                Short.class,
                Integer.class,
                String.class
        );
        HashSet<Object> set = new HashSet<>();
        set.addAll(list);
        return set.contains(clazz);
    }
}
