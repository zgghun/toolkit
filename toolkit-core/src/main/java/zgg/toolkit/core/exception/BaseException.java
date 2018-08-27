package zgg.toolkit.core.exception;

/**
 * 全局统一异常
 * Created by zgg on 2018/08/27
 */

public class BaseException extends RuntimeException {

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }
}
