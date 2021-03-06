package zgg.toolkit.system.controller;

import com.github.botaruibo.xvcode.generator.Generator;
import com.github.botaruibo.xvcode.generator.PngVCGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.system.base.BaseController;
import zgg.toolkit.system.base.BaseException;
import zgg.toolkit.system.constant.SysConst;
import zgg.toolkit.system.enums.ResultCode;
import zgg.toolkit.system.model.dto.LoginDto;
import zgg.toolkit.system.model.dto.UserSaveDto;
import zgg.toolkit.system.model.entity.User;
import zgg.toolkit.system.model.vo.LoginInfo;
import zgg.toolkit.system.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by zgg on 2018/10/26
 * 账号管理
 */
@RestController
@RequestMapping("/sys/account")
public class AccountController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private UserService userService;

    // TODO 找回密码，直接重置

    // 退出
    @RequiresAuthentication
    @GetMapping("/logout")
    public Object logout() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (SessionException ise) {
            throw new BaseException();
        }
        return commonResult();
    }

    // 登陆
    @RequiresGuest
    @PostMapping("/login")
    public Object login(@Valid LoginDto dto) {
        LoginInfo loginInfo = userService.login(dto.getUsername(), dto.getPassword(), dto.getCaptcha());
        return commonResult(loginInfo);
    }

    // 获取验证码
    @GetMapping("/captcha")
    public void generatorCaptcha(HttpServletRequest request, HttpServletResponse response) {
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        HttpSession session = request.getSession();
        int height = 40;
        int width = 140;
        int count = 4;
        Generator generator = new PngVCGenerator(width, height, count);
        try {
            generator.write2out(response.getOutputStream());
            session.setAttribute(SysConst.SESSION_CAPTCHA, generator.text());
        } catch (IOException e) {
            throw new BaseException("生成验证码错误");
        }
    }

    // 注册
    @RequiresGuest
    @PostMapping("/signUp")
    public Object signUp(@Valid UserSaveDto dto) {
        User user = userService.saveUser(dto);
        return commonResult(user);
    }

    // 未登跳转地址
    @RequestMapping("/unauth")
    public Object unAuthentication() {
        logger.warn(ResultCode.UNAUTHENTICATED.getNote());
        return commonResult(ResultCode.UNAUTHENTICATED);
    }
}
