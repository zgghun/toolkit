package zgg.toolkit.system.model.common;

import lombok.Data;
import org.apache.commons.collections.map.HashedMap;
import zgg.toolkit.system.enums.ResultCode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>返回给前台的数据模型，所有请求都是返回这个</p>
 *
 * @author ZGG
 * @date 2018/08/27
 */
@Data
public class CommonResult {

    private Integer code;
    private String msg;
    private Map<String, Object> data;

    {
        this.code = ResultCode.OK.getCode();
        this.msg = ResultCode.OK.getNote();
        this.data = new LinkedHashMap<>();
        data.put("data", "");
    }

    /**
     * 成功，不返回任何数据
     */
    public CommonResult() {
    }

    /**
     * 成功，返回数据
     *
     * @param data
     */
    public CommonResult(Object obj) {
        if (obj != null) {
            data.put("data", obj);
        }
    }

    /**
     * 返回定义好的 ResultCode
     *
     * @param resultCode 参看 {@link ResultCode}
     */
    public CommonResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getNote();
    }

    /**
     * 返回自定义错误信息
     *
     * @param resultCode
     * @param error
     */
    public CommonResult(ResultCode resultCode, Object error) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getNote();
        if (error != null) {
            this.data.put("data", error);
        }
    }


}
