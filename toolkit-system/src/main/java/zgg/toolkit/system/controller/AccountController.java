package zgg.toolkit.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.core.utils.HelpUtils;
import zgg.toolkit.system.base.SystemBaseController;
import zgg.toolkit.system.model.dto.LoginDto;
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

    // 获取用户登录信息
    @RequiresAuthentication
    @RequestMapping("/loginInfo")
    public Object getLoginUserInfo(){
        return getLoginInfo();
    }

    // 忘记密码

    // 退出

    // 获取验证码

    // 登陆
    @RequestMapping("/login")
    public Object login(@Valid LoginDto dto) {
        String username = dto.getUsername();
        String password = HelpUtils.md5(dto.getPassword());
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return commonResult("登陆成功");
    }

    // 注册

}
