package com.cwd.auth2.config.security;

import com.cwd.auth2.config.auth2.authorization.EHRAuthenticationEntryPoint;
import com.cwd.auth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/1/10.
 * spring安全框架的配置
 */
@Configuration
@EnableWebSecurity//@EnableWebMvcSecurity 注解开启Spring Security的功能
public class SecurityConfig extends WebSecurityConfigurerAdapter //继承 WebSecurityConfigurerAdapter ，并重写它的方法来设置一些web安全的细节
{

    @Autowired
    private UserService userDetailsService;
    @Autowired
    private EHRAuthenticationEntryPoint ehrAuthenticationEntryPoint;

    //==========密码加密方式====================
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
        return passwordEncoder;
    }

    //===================权限管理器======================
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager authenticationManager = super.authenticationManagerBean();
        return authenticationManager;
    }

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
                .antMatchers(
                        "/login.ftl",
                        "/logout",
                        "/resources/**",
                        "/page/**",
                        "/jsp/**");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .userDetailsService(userDetailsService)
                .anonymous().disable()
                .httpBasic().authenticationEntryPoint(ehrAuthenticationEntryPoint)
                .and()
                .formLogin()
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        System.out.println("授权失败");
                        request.getRequestDispatcher("/page/loginPage?error").forward(request, response);
                    }
                })
                .loginPage("/page/loginPage")
                .failureUrl("/page/loginPage?error")
                .loginProcessingUrl("/formLogin")  //默认  j_spring_security_check
                .passwordParameter("password")
                .usernameParameter("username")
                .successForwardUrl("/page/indexPage")
                .failureForwardUrl("/page/loginPage")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout").clearAuthentication(true)
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
                .permitAll()
        ;

    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // .passwordEncoder(passwordEncoder());

    }

}
