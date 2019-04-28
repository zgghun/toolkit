package zgg.toolkit.system.model.common;

import lombok.Data;

/**
 * Created by zgg on 2018/11/14
 */
@Data
public class MapVo {
    private Object key;
    private Object value;

    public MapVo(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public MapVo() {
    }
}
