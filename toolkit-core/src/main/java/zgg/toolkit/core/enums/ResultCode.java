package zgg.toolkit.core.enums;

/**
 * Created by zgg on 2018/10/19
 */
public enum ResultCode {

    BIND_ERROR(201, "参数绑定错误"),

    MORE_THAN_ONE_ERROR(201, "查出多个结果"),

    LOGIN_ERROR(103, "用户名或密码错误"),
    UNAUTHORIZED(102, "未授权"),
    UNAUTHENTICATED(101, "未登录"),

    BASE_ERROR(3, "其他异常"),
    ERROR(2, "系统异常"),
    OK(1, "OK");

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
