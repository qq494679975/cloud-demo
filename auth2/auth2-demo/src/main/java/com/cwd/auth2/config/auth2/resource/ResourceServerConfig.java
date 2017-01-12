package com.cwd.auth2.config.auth2.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Created by Administrator on 2017/1/11.
 * 资源服务器
 */
@Configuration
@EnableResourceServer
@Scope("singleton")
public  class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("union-resource");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .formLogin()
                .loginProcessingUrl("/login")
                .and()
                .anonymous()
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutUrl("/oauth/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                //.requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .and()
                .authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
        ;

    }

}
