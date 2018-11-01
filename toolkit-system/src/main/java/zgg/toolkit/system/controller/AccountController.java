package zgg.toolkit.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.core.enums.ResultCode;
import zgg.toolkit.system.base.SystemBaseController;
import zgg.toolkit.system.model.dto.LoginDto;
import zgg.toolkit.system.model.vo.LoginInfo;
import zgg.toolkit.system.service.AccountService;

import javax.validation.Valid;

/**
 * Created by zgg on 2018/10/26
 * 账号管理
 */
@RestController
@RequestMapping("/sys/account")
public class AccountController extends SystemBaseController {
    @Autowired
    private AccountService accountService;

    // 忘记密码

    // 退出

    // 获取验证码
    @RequestMapping("/captcha")
    public Object generatorCaptcha(){
        return commonResult("ABCDE");
    }

    // 重新获取登录用户权限信息
    @RequestMapping("/loginInfo")
    public Object getLoginUserInfo(){
        return getLoginInfo();
    }

    // 登陆
    @PostMapping("/login")
    public Object login(@Valid LoginDto dto) {
        LoginInfo loginInfo = accountService.login(dto.getUsername(), dto.getPassword(), dto.getCaptcha());
        return commonResult(loginInfo);
    }

    // 未登录错误
    @RequestMapping("/unauth")
    public Object unAuthentication(){
        return commonResult(ResultCode.UNAUTHENTICATED);
    }

    // 注册
    @RequiresPermissions("user:add")
    @PostMapping("/signUp")
    public Object signUp(){
        return commonResult("22222222");
    }

}
