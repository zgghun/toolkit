package zgg.toolkit.system.model.common;

import lombok.Data;
import zgg.toolkit.system.enums.ResultCode;

/**
 * Created by zgg on 2018/08/27
 * <p>返回给前台的数据模型，所有请求都是返回这个</p>
 */
@Data
public class CommonResult {
    /**
     * 参考 {@link ResultCode}
     */
    private Integer code;
    private String msg;
    private Object data;

    /**
     * 成功，不返回任何数据
     */
    public CommonResult() {
        this.code = ResultCode.OK.getCode();
        this.msg = ResultCode.OK.getNote();
        this.data = "";
    }

    /**
     * 成功，返回数据
     *
     * @param data
     */
    public CommonResult(Object data) {
        this.code = ResultCode.OK.getCode();
        this.msg = ResultCode.OK.getNote();
        this.data = data == null ? "" : data;
    }

    /**
     * 返回定义好的code
     *
     * @param resultCode
     */
    public CommonResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getNote();
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
        this.msg = resultCode.getNote();
        this.data = error == null ? "" : error;
    }


}
