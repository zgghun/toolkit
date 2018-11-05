package zgg.toolkit.system.base;

import org.apache.shiro.SecurityUtils;
import zgg.toolkit.core.constant.GlobalConstant;
import zgg.toolkit.core.controller.BaseController;
import zgg.toolkit.system.model.vo.LoginInfo;

/**
 * Created by zgg on 2018/10/26
 * system 模块基本 controller
 */

public class SystemBaseController extends BaseController {

    // 从 Shiro session 获取登录用户信息
    protected LoginInfo getLoginInfo(){
        return (LoginInfo) SecurityUtils.getSubject().getSession().getAttribute(GlobalConstant.SESSION_LOGIN_INFO);
    }
}
