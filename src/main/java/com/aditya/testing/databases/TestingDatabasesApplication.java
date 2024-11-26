package com.aditya.testing.databases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.aditya.testing.databases")
public class TestingDatabasesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingDatabasesApplication.class, args);
	}

}
