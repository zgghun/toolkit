package zgg.toolkit.core.model;

import lombok.Data;
import zgg.toolkit.core.enums.ResultCode;

/**
 * Created by zgg on 2018/08/27
 */
@Data
public class CommonResult {
    private Integer code;
    private String message;
    private Object data;

    /**
     * 成功，不返回任何数据
     */
    public CommonResult() {
        this.code = ResultCode.OK.getCode();
        this.message = ResultCode.OK.getNote();
        this.data = "";
    }

    /**
     * 成功，返回数据
     *
     * @param data
     */
    public CommonResult(Object data) {
        this.code = ResultCode.OK.getCode();
        this.message = ResultCode.OK.getNote();
        this.data = data == null ? "" : data;
    }

    /**
     * 返回定义好的code
     *
     * @param resultCode
     */
    public CommonResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getNote();
        this.data = "";
    }

    /**
     * 自定义错误信息
     *
     * @param resultCode
     * @param error
     */
    public CommonResult(ResultCode resultCode, Object error) {
        this.code = resultCode.getCode();
        this.message = resultCode.getNote();
        this.data = error == null ? "" : error;
    }


}
