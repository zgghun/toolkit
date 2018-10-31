package zgg.toolkit.system.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * Created by zgg on 2018/10/31
 */
@Configuration
public class ShiroConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/sys/account/logout", "logout");
        filterChainDefinitionMap.put("/sys/account/login", "anon");
        filterChainDefinitionMap.put("/**", "authc");

        // 前后端分离后，登陆地址告诉前段未登录
//        shiroFilterFactoryBean.setLoginUrl("/sys/account/unauth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(accountRealm());
//        // 自定义session管理，使用redis
//        securityManager.setSessionManager(sessionManager());
//        // 自定义cache管理，使用redis
//        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    // 开启shiro注解支持
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager());
//        return advisor;
//    }

    @Bean
    public AccountRealm accountRealm() {
        return new AccountRealm();
    }

/*    @Bean
    public SessionManager sessionManager(){
        MyShiroSessionManager sessionManager = new MyShiroSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return null;
    }

    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("192.168.56.101:6379");
        // optional properties:
//        redisManager.setTimeout(10000);
//        redisManager.setDatabase(1);
//        redisManager.setPassword("123456");
//        redisManager.setJedisPoolConfig(new JedisPoolConfig());
        return redisManager;
    }*/

}
