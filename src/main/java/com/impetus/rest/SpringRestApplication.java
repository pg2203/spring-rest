package com.impetus.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringRestApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SpringRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(){
		return (args) -> {
			log.info("Iniside CommandLine Runner");
		};
	}
}
