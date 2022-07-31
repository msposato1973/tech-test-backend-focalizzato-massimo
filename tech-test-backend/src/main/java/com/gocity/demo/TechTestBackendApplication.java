package com.gocity.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class TechTestBackendApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TechTestBackendApplication.class);

	/***
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.info("main - SpringApplication.run: begin");
		SpringApplication.run(TechTestBackendApplication.class, args);
	}

}
