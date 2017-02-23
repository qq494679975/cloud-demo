package com.cwd.auth2.config.auth2.authorization;

import com.cwd.auth2.service.EHRAuthorizationCodeServices;
import com.cwd.auth2.service.UserService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/1/11.
 * 授权服务器的配置
 * AuthorizationEndpoint
 */
@Configuration
@EnableAuthorizationServer
@EnableTransactionManagement//以防止在创建令牌时竞争相同行的客户端应用程序之间发生冲突
@Scope("singleton")
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private CWDJdbcClientDetailsService jdbcClientDetailsService;
    @Autowired
    private EHRAuthorizationCodeServices ehrAuthorizationCodeServices;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private CWDAuthenticationEntryPoint ehrAuthenticationEntryPoint;

    /**
     * 定义客户端详细信息服务的configurer。 客户端详细信息可以初始化，也可以只引用现有存储。
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.
                withClientDetails(jdbcClientDetailsService)
        ;

    }

    /**
     * 定义令牌端点上的安全约束
     *
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .allowFormAuthenticationForClients()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        System.out.println("AccessDeniedHandler");
                    }
                })
                .authenticationEntryPoint(ehrAuthenticationEntryPoint);
    }

    /**
     * 定义授权和令牌端点以及令牌服务
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        endpoints
                .userDetailsService(userService)//如果你注入一个UserDetailsService或者如果一个是全局配置的（例如在一个GlobalAuthenticationManagerConfigurer ），刷新令牌授权将包含对用户详细信息的检查，以确保帐户仍然活动
                .authorizationCodeServices(ehrAuthorizationCodeServices)//定义授权代码授权的授权代码服务
                .tokenStore(getJdbeTokenStore())//使用数据库证书
                .authenticationManager(authenticationManager);//注入AuthenticationManager打开密码授权。

    }

    //=========================配置数据库证书=========================
    @Bean(name = "ehrJdbeTokenStore")
    public JdbcTokenStore getJdbeTokenStore() {
        JdbcTokenStore jdbcTokenStore = new JdbcTokenStore(dataSource);

        return jdbcTokenStore;
    }

    //=========================配置证书服务类=========================
    @Bean
    @Primary
    public DefaultTokenServices ehrTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setSupportRefreshToken(true);//支持更新
        defaultTokenServices.setTokenStore(getJdbeTokenStore());
        return defaultTokenServices;
    }
    /**
     * 扩展Spring Security 默认的 AccessDecisionManager
     * 添加对OAuth中 scope 的检查与校验
     *
     * @return
     */
//    @Bean
//     @Primary
//    private UnanimousBased oauth2AccessDecisionManager() {
//        List<AccessDecisionVoter<?>> voters = new ArrayList<>();
//        voters.add(new ScopeVoter());
//        voters.add(new RoleVoter());
//        voters.add(new AuthenticatedVoter());
//        UnanimousBased unanimousBased = new UnanimousBased(voters);
//        return unanimousBased;
//
//    }

}