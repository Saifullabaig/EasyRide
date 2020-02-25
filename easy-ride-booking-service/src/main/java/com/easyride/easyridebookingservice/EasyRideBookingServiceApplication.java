package com.easyride.easyridebookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EasyRideBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyRideBookingServiceApplication.class, args);
	}

}
