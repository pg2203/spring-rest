package com.impetus.rest;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.impetus.rest.config.ElasticSearchProperties;
import com.impetus.rest.domain.User;
import com.impetus.rest.repo.UserRepository;

@SpringBootApplication
public class SpringRestApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SpringRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}
	
	@Autowired
	private ElasticSearchProperties esConfig;
	
	@Bean
	public CommandLineRunner demo(UserRepository userRepo){
		return (args) -> {
			log.info("Iniside CommandLine Runner");
			log.info("ES property ->"+esConfig.getClusterName());
			
			User user = new User();
			user.setName("name1");
			user.setAge(25);
			
			userRepo.saveAndFlush(user);
			
			log.info("Users found in db");
			log.info(userRepo.findAll().stream().map(u -> u.toString()).collect(Collectors.joining(",")));
		};
		
		
	}
}
