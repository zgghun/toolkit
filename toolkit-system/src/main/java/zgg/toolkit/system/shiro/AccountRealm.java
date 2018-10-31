package zgg.toolkit.system.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import zgg.toolkit.system.model.entity.User;
import zgg.toolkit.system.model.vo.LoginInfo;
import zgg.toolkit.system.service.AccountService;

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
        User user = (User) principals.getPrimaryPrincipal();
        LoginInfo loginInfo = accountService.getLoginInfo(user.getId());
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(loginInfo.getPermissions());
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
        // 去掉密码
        user.setPassword("");
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
