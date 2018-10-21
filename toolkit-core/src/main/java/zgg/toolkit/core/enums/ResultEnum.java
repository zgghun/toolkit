package zgg.toolkit.core.enums;

/**
 * Created by zgg on 2018/10/19
 */
public enum ResultEnum implements BaseEnum{

    BIND_ERROR(101, "参数绑定错误"),
    FAIL(100, "系统异常"),
    OK(1, "成功");

    private int code;
    private String note;

    ResultEnum(int code, String note) {
        this.code = code;
        this.note = note;
    }

    public int getCode() {
        return code;
    }

    public String getNote() {
        return note;
    }
}
