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

    public CommonResult() {
        this.code = ResultCode.OK.getCode();
        this.message = ResultCode.OK.getNote();
        this.data = "";
    }

    public CommonResult(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getNote();
        this.data = "";
    }

    public CommonResult(Object data) {
        this.code = ResultCode.OK.getCode();
        this.message = ResultCode.OK.getNote();
        this.data = data == null ? "" : data;
    }

    public CommonResult(ResultCode resultEnum, Object data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getNote();
        this.data = data;
    }


}
