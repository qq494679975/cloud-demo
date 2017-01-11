package com.cwd.auth2.config;

import com.cwd.auth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2017/1/10.
 */
@Configuration
@EnableWebSecurity//@EnableWebMvcSecurity 注解开启Spring Security的功能
public class SecurityConfig extends WebSecurityConfigurerAdapter //继承 WebSecurityConfigurerAdapter ，并重写它的方法来设置一些web安全的细节
{
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserService userService;

    /**
     * 改成数据库查询账号密码
     *
     * @return
     */
    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //禁用CSRF保护
                .csrf().disable()
                .authorizeRequests()//定义哪些URL需要被保护、哪些不需要被保护。例如以上代码指定了 / 和 /home 不需要任何认证就可以访问，其他的路径都必须通过身份验证。
                .antMatchers("/login/**",
                        "/swagger-ui.html",
                        "/configuration/**",
                        "/v2/api-docs/**",
                        "/webjars/springfox-swagger-ui/**").permitAll()
                //任何访问都必须授权
                .antMatchers("/user/**").authenticated()
                .and()
                //表单登陆
                .formLogin()   //允许用户使用表单认证
                .loginProcessingUrl("/user/login").usernameParameter("username").passwordParameter("password")
                //登陆成功后的处理，因为是API的形式所以不用跳转页面
                .and()
                .httpBasic() //允许用户使用HTTP基本身份验证进行身份验证
                .and()
                //登出
                .logout()
                // .logoutUrl("/my/logout")//触发注销的URL（默认为/logout）。如果启用CSRF保护（默认），则请求也必须是POST。
                // .logoutSuccessUrl("/my/index")//发生注销后重定向到的URL。默认值为/login?logout
                .invalidateHttpSession(true) //清空session
                // .addLogoutHandler(logoutHandler)
                //.logoutSuccessHandler(new RestLogoutSuccessHandler())//LogoutSuccessHandler在LogoutFilter成功注销后被调用，以处理例如。 重定向或转发到适当的目的地。 注意，该接口几乎与LogoutHandler相同，但可能引发异常
                ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        JdbcUserDetailsManagerConfigurer detailsManagerConfigurer = auth.jdbcAuthentication();

        detailsManagerConfigurer.dataSource(dataSource)
                .usersByUsernameQuery(getUserQuery())
                .authoritiesByUsernameQuery(getAuthoritiesQuery());

        detailsManagerConfigurer.
                getUserDetailsService().
                setCreateUserSql(" insert into users (id,user, pw, status) values ('111',?,?,?)");

    }

    public String getUserQuery() {
        return " select " +
                " u.user username," +
                " u.pw password " +
                " from " +
                " users u " +
                " where u.user = ?";
    }

    public String getAuthoritiesQuery() {
        return " SELECT " +
                " a.authority authority , " +
                " u.user username " +
                "FROM " +
                " authorities a , " +
                " users u " +
                "where " +
                " a.username=u.`user`";
    }
}
