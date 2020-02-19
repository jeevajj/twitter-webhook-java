package com.twitter.activity.userdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.twitter.*")
@EnableJpaRepositories(basePackages = "com.twitter.*")
@ComponentScan(basePackages = "com.twitter.*")
public class UserDmApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDmApplication.class, args);
	}

}
