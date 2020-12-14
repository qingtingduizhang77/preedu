package com.tware.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// 建议删除这个类，bean加载的次序有问题，会引起一序列的bug
@Configuration
@EnableSwagger2
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String redishost;

    @Value("${spring.redis.port}")
    private int redisport;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.password}")
    private String password;


    @Bean
    public ShiroFilterFactoryBean shirFilter(org.apache.shiro.mgt.SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //shiroFilterFactoryBean.getFilters().put("authc",jsonFormAuthenticationFilter());
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/public/**", "anon");
        filterChainDefinitionMap.put("/api/public/**", "anon");
        filterChainDefinitionMap.put("/api/external/**", "anon");
       
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources", "anon");
        filterChainDefinitionMap.put("/swagger-resources/configuration/security", "anon");
        filterChainDefinitionMap.put("/swagger-resources/configuration/ui", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");

        
        //api
        filterChainDefinitionMap.put("/preedu-api/account/login", "anon");
        filterChainDefinitionMap.put("/preedu-api/account/autologin", "anon");
        filterChainDefinitionMap.put("/preedu-api/account/checkUpdate", "anon");
        filterChainDefinitionMap.put("/preedu-api/account/getPhoneCode", "anon");
        filterChainDefinitionMap.put("/preedu-api/account/uploadFile", "anon");

        
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
       // filterChainDefinitionMap.put("/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/api/public/loginFailure");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/loginSuccessful");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /** 认证信息源
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }


    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager =new RedisManager();
        redisManager.setHost(redishost);
        redisManager.setPort(redisport);
        redisManager.setDatabase(database);
        if(password!=null && !password.trim().equals(""))
        {
            redisManager.setPassword(password);
        }

       // password
      //  redisManager.setExpire(3600000);// 配置缓存过期时间
        redisManager.setTimeout(3600000);
        return redisManager;
    }

//    /**
//     * Session Manager
//     * 使用的是shiro-redis开源插件
//     */
//    @Bean
//    public DefaultWebSessionManager sessionManager() {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setSessionDAO(redisSessionDAO());
//        return sessionManager;
//    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        //JacksonRedisSerializer  valueSerializer=new JacksonRedisSerializer(Session.class);
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
     //   redisSessionDAO.setValueSerializer(valueSerializer);
        return redisSessionDAO;
    }




    @Bean
    public SessionRedisUtil sessionRedisUtil(){
    	SessionRedisUtil sessionRedisUtil = new SessionRedisUtil();
        return sessionRedisUtil;
    	
    }


    @Bean
    public org.apache.shiro.mgt.SecurityManager  securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setCacheManager(cacheManager());
        securityManager.setRealm(shiroRealm());
        LJNativeSessionManager sessionManager=new LJNativeSessionManager(sessionRedisUtil());
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setGlobalSessionTimeout(3600000);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

//    @Bean
//    public ShiroAuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
//        ShiroAuthorizationAttributeSourceAdvisor shiroAuthorizationAttributeSourceAdvisor = new ShiroAuthorizationAttributeSourceAdvisor();
//        shiroAuthorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
//        return shiroAuthorizationAttributeSourceAdvisor;
//    }

    
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    
    @Bean
    public JsonFormAuthenticationFilter jsonFormAuthenticationFilter() {
        return new JsonFormAuthenticationFilter();
    }
}
