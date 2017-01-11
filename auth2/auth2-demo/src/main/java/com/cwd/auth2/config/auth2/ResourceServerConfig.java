package com.cwd.auth2.config.auth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Administrator on 2017/1/11.
 * 资源服务器
 */
@Configuration
@EnableResourceServer
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
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .logout()
                .logoutUrl("/oauth/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .and()
                .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1.0/tokens").permitAll()
                .antMatchers("/**").authenticated()
//                    .antMatchers("/**").permitAll()
        ;

    }

}
