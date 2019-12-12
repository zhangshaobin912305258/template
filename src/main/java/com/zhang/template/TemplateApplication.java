package com.zhang.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan(value = "com.zhang.template.mapper")
public class TemplateApplication {

	private static String port;

	public static String getPort() {
		return port;
	}

	@Value("${server.port}")
	public static void setPort(String port) {
		TemplateApplication.port = port;
	}

	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
	}

	/**
	 * 跨域支持
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**");
			}
		};
	}

}
