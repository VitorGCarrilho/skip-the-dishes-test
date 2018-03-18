package com.skipthedishes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SkipCustomerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SkipCustomerApplication.class, args);
	}
}
