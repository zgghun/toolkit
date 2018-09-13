package zgg.toolkit.core.controller;

import zgg.toolkit.core.model.CommonResult;

/**
 * Created by zgg on 2018/08/27
 */

public class BaseController {

    protected CommonResult commonResult(){
        return new CommonResult();
    }

    protected CommonResult commonResult(Object date){
        return new CommonResult(date);
    }
}
