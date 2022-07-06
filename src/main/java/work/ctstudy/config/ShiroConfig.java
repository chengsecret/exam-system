package work.ctstudy.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.ctstudy.shiro.RedisCacheManager;
import work.ctstudy.shiro.realm.MyRealm;

import java.util.HashMap;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager defaultSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager);

        HashMap<String, String> map = new HashMap<>();

        map.put("/","anon"); //首页
        map.put("/index","anon");
        map.put("/index.html","anon");

        map.put("/user/logout","anon"); //登出
        map.put("/user/verifyCodePicture","anon"); //验证码
        map.put("/user/toRegister","anon"); //注册
        map.put("/user/toLogin","anon"); ///登录
        map.put("/user/register","anon"); //注册页面
        map.put("/user/login","anon"); //登录页面

        map.put("/boot/**","anon");  //静态资源
        map.put("/commons/**","anon"); //公共部分资源


        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/user/login"); //登录页面


        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("getRealm") Realm realm) {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(realm);
        return defaultSecurityManager;
    }

    @Bean
    public Realm getRealm() {
        MyRealm realm = new MyRealm();

        //设置凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);

        //开启缓存管理
        realm.setCacheManager(new RedisCacheManager()); //设置自定义的缓存管理器
        realm.setCachingEnabled(true);//开启全局缓存
        realm.setAuthenticationCachingEnabled(true);//开启认证缓存
        realm.setAuthenticationCacheName("authenticationCache"); //设置认证缓存的统一名称
        realm.setAuthorizationCachingEnabled(true); //开启授权缓存
        realm.setAuthorizationCacheName("authorizationCache"); //设置授权缓存的统一名称

        return realm;
    }

    // 注入ShiroDialect，用来整合 Shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
