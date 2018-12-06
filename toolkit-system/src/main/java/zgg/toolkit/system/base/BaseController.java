package zgg.toolkit.system.base;

import org.apache.shiro.SecurityUtils;
import zgg.toolkit.system.constant.SysConst;
import zgg.toolkit.system.enums.ResultCode;
import zgg.toolkit.system.model.common.CommonResult;
import zgg.toolkit.system.model.vo.LoginInfo;

/**
 * Created by zgg on 2018/08/27
 */

public class BaseController {

    // 从 Shiro session 获取登录用户信息
    protected LoginInfo getLoginInfo(){
        return (LoginInfo) SecurityUtils.getSubject().getSession().getAttribute(SysConst.SESSION_LOGIN_INFO);
    }

    protected CommonResult commonResult() {
        return new CommonResult();
    }

    protected CommonResult commonResult(ResultCode resultCode) {
        return new CommonResult(resultCode);
    }

    protected CommonResult commonResult(Object date) {
        return new CommonResult(date);
    }
}