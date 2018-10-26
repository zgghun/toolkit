package zgg.toolkit.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    // 忘记密码

    // 退出

    // 获取验证码

    // 登陆
    @RequestMapping("/login")
    public Object login(@Valid LoginDto dto) {


        return null;
    }

    // 注册

}
