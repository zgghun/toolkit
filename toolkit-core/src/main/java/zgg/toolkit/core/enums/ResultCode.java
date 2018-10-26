package zgg.toolkit.core.enums;

/**
 * Created by zgg on 2018/10/19
 */
public enum ResultCode {

    BIND_ERROR(201, "参数绑定错误"),

    SYSTEM_ERROR(103, "系统异常"),

    UNAUTHORIZED(102, "未授权"),
    UNAUTHENTICATED(101, "未登录"),

    OK(1, "成功");

    private int code;
    private String note;

    ResultCode(int code, String note) {
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
