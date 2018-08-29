package zgg.toolkit.core.bean;

import lombok.Data;

/**
 * Created by zgg on 2018/08/27
 */
@Data
public class BaseResult {
    private Integer code;
    private String message;
    private Object data;

    public BaseResult() {
        this.code = 200;
        this.message = "ok";
        this.data = null;
    }

    public BaseResult(Object data) {
        this.code = 200;
        this.message = "ok";
        this.data = data;
    }

    public BaseResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


}
