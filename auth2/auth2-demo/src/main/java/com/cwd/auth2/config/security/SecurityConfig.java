package com.cwd.auth2.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Administrator on 2017/1/10.
 * spring安全框架的配置
 */
@Configuration
@EnableWebSecurity//@EnableWebMvcSecurity 注解开启Spring Security的功能
public class SecurityConfig extends WebSecurityConfigurerAdapter //继承 WebSecurityConfigurerAdapter ，并重写它的方法来设置一些web安全的细节
{

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 不过滤的请求
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                //swagger-ui界面---start
                .antMatchers("/swagger-ui.html")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/v2/api-docs/**")
                .antMatchers("/configuration/**")
                .antMatchers("/webjars/springfox-swagger-ui/**")
                //swagger-ui界面---end
                .antMatchers("/login/**")
                .antMatchers("/login.do")
                .antMatchers("/oauth/**");


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder= NoOpPasswordEncoder.getInstance();
        return passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
