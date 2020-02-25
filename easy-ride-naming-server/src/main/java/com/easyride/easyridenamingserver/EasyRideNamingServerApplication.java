package com.easyride.easyridenamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EasyRideNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyRideNamingServerApplication.class, args);
	}

}
