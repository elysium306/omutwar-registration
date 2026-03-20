package com.omutwar.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatabaseProjectDemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DatabaseProjectDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DatabaseProjectDemoApplication.class, args);
		log.info("### Spring Application Has Been Initialized ###");
	}

}
