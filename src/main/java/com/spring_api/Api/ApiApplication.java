package com.spring_api.Api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ApiApplication.class, args);
	}

}
