package com.project.urlshortener.configuration.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.project.urlshortener")
@EnableMongoRepositories(basePackages = "com.project.urlshortener.repository")
public class UrlShortenerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerServiceApplication.class, args);
	}

}
