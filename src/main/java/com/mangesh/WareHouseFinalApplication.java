package com.mangesh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WareHouseFinalApplication {

	private static final Logger log = LoggerFactory.getLogger(WareHouseFinalApplication.class);

	public static void main(String[] args) {
		log.info("WareHouseFinalApplication.main() started...!!");
		SpringApplication.run(WareHouseFinalApplication.class, args);
	}

}
