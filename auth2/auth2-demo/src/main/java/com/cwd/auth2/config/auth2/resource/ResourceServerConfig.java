package com.cwd.auth2.config.auth2.resource;

import com.cwd.auth2.config.auth2.authorization.CWDAuthenticationEntryPoint;
import com.cwd.auth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/11.
 * 资源服务器
 */
//@Order(2)
//@Configuration
//@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private UserService userDetailsService;
    @Autowired
    private CWDAuthenticationEntryPoint ehrAuthenticationEntryPoint;
    @Resource(name = "ehrJdbeTokenStore")
    private JdbcTokenStore jdbcTokenStore;
    @Autowired
    private ResourceServerTokenServices resourceServerTokenServices;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.
                tokenServices(resourceServerTokenServices).
                tokenStore(jdbcTokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .userDetailsService(userDetailsService)
                .anonymous().disable()
                .httpBasic().authenticationEntryPoint(ehrAuthenticationEntryPoint)
                .and()
                .formLogin()
                .loginPage("/page/loginPage")
                .failureUrl("/page/loginPage?error")
                .loginProcessingUrl("/formLogin")  //默认  j_spring_security_check
                .passwordParameter("password")
                .usernameParameter("username")
                //.successForwardUrl("/page/oauthApproval")
                .failureForwardUrl("/page/loginPage?error")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .and()
                .authorizeRequests()
                .anyRequest()
                .fullyAuthenticated()
                .antMatchers(
                        "/api/v1.0/tokens",
                        "/page/**",
                        "/logout",
                        "/templates/**",
                        "/resources/**",
                        "/js/**",
                        "/img/**",
                        "/static/**",
                        "/javascript/**",
                        "/jsp/**")
                .permitAll();
    }

}
