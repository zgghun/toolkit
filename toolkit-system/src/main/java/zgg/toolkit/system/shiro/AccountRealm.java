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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zgg on 2018/10/25
 */
public class AccountRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(AccountRealm.class);

    @Autowired
    private AccountService accountService;

    // 获取权限信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        LoginInfo loginInfo = accountService.getLoginInfo(user);
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<String> permissions = loginInfo.getPermissions();
        // 不知道什么原因，permissions为null的话shiro会报空指针异常
        if (permissions == null) {
            permissions = new ArrayList<>();
        }
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
            throw new AccountException();
        }
        // 去掉密码
        user.setPassword("********");
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
