package zgg.toolkit.system.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zgg.toolkit.core.constant.GlobalConstant;
import zgg.toolkit.core.enums.ResultCode;
import zgg.toolkit.core.exception.BaseException;
import zgg.toolkit.core.utils.HelpUtils;
import zgg.toolkit.system.base.SystemBaseService;
import zgg.toolkit.system.mapper.AccountMapper;
import zgg.toolkit.system.mapper.autogen.UserMapper;
import zgg.toolkit.system.model.entity.User;
import zgg.toolkit.system.model.entity.UserExample;
import zgg.toolkit.system.model.vo.LoginInfo;

import java.util.List;

/**
 * Created by zgg on 2018/10/25
 */
@Service
public class AccountService extends SystemBaseService {
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountMapper accountMapper;


    /**
     * 登陆
     *
     * @param username 用户名、电话、邮箱
     * @param password 明文密码
     * @param captcha  验证码
     */
    public LoginInfo login(String username, String password, String captcha) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, HelpUtils.md5(password));
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            log.debug("登陆错误：{}", e.getMessage());
            throw e;
        }
        User user = (User) subject.getPrincipals().getPrimaryPrincipal();
        // 把登陆信息存到session中,同时返回登陆信息
        LoginInfo loginInfo = this.getLoginInfo(user.getId());

        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(GlobalConstant.SESSION_LOGIN_INFO, loginInfo);
        session.removeAttribute(GlobalConstant.SESSION_CAPTCHA);
        return new LoginInfo();
    }

    /**
     * shiro 查询用户
     *
     * @param username
     * @param password
     * @return
     */
    public User getUser(String username, String password) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (HelpUtils.isNumeric(username)) {
            // 全数字，电话登陆
            criteria.andTelEqualTo(username);
        } else if (HelpUtils.contains(username, "@")) {
            // 包含 @ 邮箱登陆
            criteria.andEmailEqualTo(username);
        } else {
            // 用户名登陆
            criteria.andUsernameEqualTo(username);
        }
        criteria.andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 1) {
            return users.get(0);
        }
        if (users.size() > 1) {
            throw new BaseException(ResultCode.MORE_THAN_ONE_ERROR);
        }
        return null;
    }

    /**
     * 获取用户信息、权限、可访问模块
     *
     * @param userId
     * @return
     */
    public LoginInfo getLoginInfo(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId(userId);
        loginInfo.setUsername(user.getUsername());
        loginInfo.setTel(user.getTel());
        loginInfo.setAvatar(user.getAvatar());


        return loginInfo;
    }
}
