package zgg.toolkit.system.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * Created by zgg on 2018/10/31
 * shiro的springboot集成 http://shiro.apache.org/spring-boot.html，
 *                      https://github.com/apache/shiro/tree/master/samples/spring-boot-web
 * 利用redis管理缓存参考shiro-redis： https://github.com/alexxiyang/shiro-redis-spring-tutorial
 */
@Configuration
public class ShiroConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/sys/account/login", "anon");
        filterChainDefinitionMap.put("/sys/account/captcha", "anon");
        filterChainDefinitionMap.put("/sys/account/signUp", "anon");
        filterChainDefinitionMap.put("/error/*", "anon");
        filterChainDefinitionMap.put("/**", "authc");

        // 通过shiro设置登陆地址（默认login.jsp），由于前后分离了，此地址用于未登录时返回未登录异常信息
        shiroFilterFactoryBean.setLoginUrl("/sys/account/unauth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }


    @Bean
    public Realm realm() {
        return new AccountRealm();
    }

    // shiro 注解（需要spring aop支持）
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
