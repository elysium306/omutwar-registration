package com.omutwar.registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OmutwarRentalAnalyticsPlatformApplication {
	private static final Logger log = LoggerFactory.getLogger(OmutwarRentalAnalyticsPlatformApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OmutwarRentalAnalyticsPlatformApplication.class, args);
		log.info("### Spring Application Has Been Initialized ###");
	}

}
