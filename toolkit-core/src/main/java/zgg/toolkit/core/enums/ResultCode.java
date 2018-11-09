package zgg.toolkit.core.enums;

/**
 * Created by zgg on 2018/10/19
 */
public enum ResultCode {

    LOGIN_ERROR(203, "用户名或密码错误"),
    UNAUTHORIZED(202, "未授权"),
    UNAUTHENTICATED(201, "未登录"),
    BASE_ERROR(200, "其他异常"),

    BIND_ERROR(103, "参数绑定错误"),
    MORE_THAN_ONE_ERROR(102, "查出多个结果"),
    ADDRESS_ERROR(101, "请求地址不存在"),
    ERROR(100, "系统异常"),

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
