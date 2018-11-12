package zgg.toolkit.core.enums;

/**
 * Created by zgg on 2018/10/19
 * 返回状态码
 */
public enum ResultCode {

    //
    OK(1, "OK"),

    ERROR(100, "系统异常"),
    ADDRESS_ERROR(101, "请求地址不存在"),
    MORE_THAN_ONE_ERROR(102, "查出多个结果"),
    BIND_ERROR(103, "参数绑定错误"),

    BASE_ERROR(200, "其他异常"),
    UNAUTHENTICATED(201, "未登录"),
    UNAUTHORIZED(202, "未授权"),
    LOGIN_ERROR(203, "用户名或密码错误"),

    NOT_FOUND_ERROR(210, "数据不存在");

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
