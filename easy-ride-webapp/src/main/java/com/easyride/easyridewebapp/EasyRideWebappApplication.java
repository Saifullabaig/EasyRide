package com.easyride.easyridewebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EasyRideWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyRideWebappApplication.class, args);
	}

}
