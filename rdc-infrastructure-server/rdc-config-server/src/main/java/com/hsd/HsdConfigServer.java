package com.hsd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableEurekaClient
@EnableConfigServer
@SpringCloudApplication
@Slf4j

@EnableHystrixDashboard
@EnableCircuitBreaker
public class HsdConfigServer {

	public static void main(String[] args) {

		SpringApplication.run(HsdConfigServer.class, args);
		log.info("Application is success!");
	}
}
