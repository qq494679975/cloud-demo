package com.cwd.auth2.config.auth2.authorization;

import com.cwd.auth2.service.UserService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.vote.ScopeVoter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 * 授权服务器的配置
 */
@Configuration
@EnableAuthorizationServer
@Scope("singleton")
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EHRJdbcClientDetailsService jdbcClientDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        endpoints
                .userDetailsService(userService)
                .tokenStore(getJdbeTokenStore())
                .authenticationManager(authenticationManager);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.
                withClientDetails(jdbcClientDetailsService);

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients();
    }

    //=========================配置数据库证书=========================
    @Bean
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