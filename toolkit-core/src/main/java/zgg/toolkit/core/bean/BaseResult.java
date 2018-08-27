package zgg.toolkit.core.bean;

/**
 * Created by zgg on 2018/08/27
 */

public class BaseResult {
    private Integer code;
    private String msg;
    private Object data;

    public BaseResult() {
        this.code = 200;
        this.msg = "ok";
        this.data = null;
    }

    public BaseResult(Object data) {
        this.code = 200;
        this.msg = "ok";
        this.data = data;
    }

    public BaseResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
