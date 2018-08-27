package zgg.toolkit.core.controller;

import zgg.toolkit.core.bean.BaseResult;

/**
 * Created by zgg on 2018/08/27
 */

public class BaseController {

    protected BaseResult result(){
        return new BaseResult();
    }

    protected BaseResult result(Object date){
        return new BaseResult(date);
    }
}
