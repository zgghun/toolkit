package zgg.toolkit.core.model;

import lombok.Data;
import zgg.toolkit.core.enums.ResultEnum;

/**
 * Created by zgg on 2018/08/27
 */
@Data
public class CommonResult {
    private Integer code;
    private String message;
    private Object data;

    public CommonResult() {
        this.code = ResultEnum.OK.getCode();
        this.message = ResultEnum.OK.getNote();
        this.data = "";
    }

    public CommonResult(Object data) {
        this.code = ResultEnum.OK.getCode();
        this.message = ResultEnum.OK.getNote();
        this.data = data == null ? "" : data;
    }

    public CommonResult(ResultEnum resultEnum, Object data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getNote();
        this.data = data;
    }


}
