package com.bharath.college_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class CollegeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeManagementApplication.class, args);
	}

}
