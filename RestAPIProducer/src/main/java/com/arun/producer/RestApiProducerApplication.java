package com.arun.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RestApiProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiProducerApplication.class, args);
	}

}
