package com.telnet.jukebox.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringJukeboxWebServiceApplication extends SpringBootServletInitializer {
	
	public static final Logger logger= LogManager.getLogger(SpringJukeboxWebServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJukeboxWebServiceApplication.class, args);
		logger.info("Jukebox application is started!");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringJukeboxWebServiceApplication.class);
	}
}
