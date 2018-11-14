package zgg.toolkit.core.model;

import lombok.Data;

/**
 * Created by zgg on 2018/11/14
 */
@Data
public class MapVO {
    private Object key;
    private Object value;

    public MapVO(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public MapVO() {

    }
}
