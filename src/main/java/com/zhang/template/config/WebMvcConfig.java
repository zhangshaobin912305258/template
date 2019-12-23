package com.zhang.template.config;

import com.zhang.template.filter.ReplaceStreamFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
      }
    };
  }

 /* @Bean
  public FilterRegistrationBean replaceStreamFilter() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new ReplaceStreamFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.addServletNames("replaceStreamFilter");
    return filterRegistrationBean;
  }*/

}
