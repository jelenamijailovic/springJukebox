package com.telnet.jukebox.spring.swagger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.telnet.jukebox.spring.controller.ArtistResource;
import com.telnet.jukebox.spring.model.Artist;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@PropertySource("classpath:swagger.properties")
@EnableSwagger2
@ComponentScan(basePackageClasses= ArtistResource.class)
public class SwaggerConfig extends WebMvcConfigurationSupport{

	public static final String title = "JukeboxSwagger";
	public static final String SWAGGER_API_VERSION = "1.0";
	public static final String description = "RESTful api for Jukebox";
	public static final String LICENCE = "Licence";

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(title).version(SWAGGER_API_VERSION).license(LICENCE).description(description).build();

	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).pathMapping("/").select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}
	
	@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
 /*
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");*/
    }

}
