package com.zhang.template.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  UserDetailsService userDetailsService;

    /**
     * 自定义身份验证组件
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用自定义身份验证组件
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf 
        http.cors().and().csrf().disable().authorizeRequests()
                //跨域预检请求
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                //webjars
                .antMatchers("/webjars/**").permitAll()
                //首页和登录页面
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                //验证码
                .antMatchers("/captcha.jpg**").permitAll()
                //服务监控
                .antMatchers("/actuator/**").permitAll()
                //其他所有请求需要进行身份认证
                .anyRequest().authenticated();
        http.headers().frameOptions().disable();
        //退出登录器
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        //token验证过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
 