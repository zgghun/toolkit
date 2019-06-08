package zgg.toolkit.system.enums;

/**
 * 返回状态码
 * 只有 1 为正常
 * @date 2018/10/19
 * @author nerve
 */
public enum ResultCode {
    // 全局统一异常代码
    OK(1, "OK"),

    ERROR(100, "系统异常"),
    ERROR_OTHERS(200, "其他异常"),

    ERROR_URI_NOT_FOUND(101, "请求地址不存在"),
    ERROR_PARAM_BIND(103, "参数绑定错误"),

    UNAUTHENTICATED(201, "未登录"),
    UNAUTHORIZED(202, "未授权"),
    ERROR_LOGIN(203, "用户名或密码错误"),

    ERROR_DATABASE(104, "数据库错误"),
    ERROR_DATA_NOT_EXIST(210, "数据不存在"),
    ERROR_DATA_NOT_UNIQUE(211, "数据不唯一");

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
