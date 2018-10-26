package zgg.toolkit.core.exception;

import zgg.toolkit.core.enums.ResultCode;

/**
 * 全局统一异常
 * Created by zgg on 2018/08/27
 */

public class BaseException extends RuntimeException {
    private ResultCode resultCode;

    public BaseException() {
        super();
    }

    public BaseException(String msg){
        super(msg);
    }

    public BaseException(ResultCode resultCode){
        super();
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
