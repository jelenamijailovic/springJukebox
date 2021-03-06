package com.telnet.jukebox.spring.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class Cors implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/api/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "OPTIONS", "POST")
				.allowedHeaders("Content-Type", "X-Requested-With", "Access-Control-Allow-Headers", "Authorization")
				//.exposedHeaders("header1", "header2")
				.allowCredentials(true).maxAge(3600);

	}
}
