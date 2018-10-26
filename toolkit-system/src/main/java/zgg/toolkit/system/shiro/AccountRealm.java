package zgg.toolkit.system.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import zgg.toolkit.core.constant.GlobalConstant;
import zgg.toolkit.system.model.entity.User;
import zgg.toolkit.system.model.vo.LoginInfo;
import zgg.toolkit.system.service.AccountService;

import java.util.List;

/**
 * Created by zgg on 2018/10/25
 */
//@Component
public class AccountRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AccountService accountService;

    // 获取权限信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LoginInfo loginInfo = (LoginInfo) principals.getPrimaryPrincipal();
        // 登陆时把权限存到了 session 中，这里可直接获取
        List<String > permissions = loginInfo.getPermissions();
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }


    // 登陆验证： 调用 subject.login(token) 时会调用此方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        User user = accountService.getUser(username, password);
        if (user == null) {
            throw new UnknownAccountException();
        }

        LoginInfo loginInfo = new LoginInfo();
        SecurityUtils.getSubject().getSession().setAttribute(GlobalConstant.SESSION_LOGIN_INFO, loginInfo);
        return new SimpleAuthenticationInfo(loginInfo, password, getName());
    }
}
