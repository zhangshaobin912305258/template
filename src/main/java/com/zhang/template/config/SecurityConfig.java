package com.zhang.template.config;

import com.zhang.template.filter.JwtTokenFilter;
import com.zhang.template.security.JwtAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;

  public SecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 使用自定义身份验证组件
    auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        // 因为使用JWT，所以不需要HttpSession
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        // OPTIONS请求全部放行
        .antMatchers(HttpMethod.OPTIONS, "/**")
        .permitAll()
        // 登录接口放行
        .antMatchers("/login")
        .permitAll()
        //swagger地址
        .antMatchers("/doc.html**")
        .permitAll()
        // 首页和登录页面
        .antMatchers("/")
        .permitAll()
        .antMatchers("/login")
        .permitAll()
        // 验证码
        .antMatchers("/captcha.jpg**")
        .permitAll()
        // 服务监控
        .antMatchers("/actuator/**")
        .permitAll()
        // 其他接口全部接受验证
        .anyRequest()
        .authenticated();

    // 使用自定义的 Token过滤器 验证请求的Token是否合法
    http.addFilterBefore(
        authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    http.headers().cacheControl();
  }

  @Bean
  public JwtTokenFilter authenticationTokenFilterBean() throws Exception {
    return new JwtTokenFilter();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
