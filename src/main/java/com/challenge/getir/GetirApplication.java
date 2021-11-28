package com.challenge.getir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoRepositories("com.challenge.getir.repository")
@EnableScheduling
public class GetirApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetirApplication.class, args);
	}

}
