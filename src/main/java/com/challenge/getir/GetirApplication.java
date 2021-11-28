package com.challenge.getir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.challenge.getir.repository")
public class GetirApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetirApplication.class, args);
	}

}
