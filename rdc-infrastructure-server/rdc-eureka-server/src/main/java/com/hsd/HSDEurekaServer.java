package com.hsd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableEurekaServer
@SpringCloudApplication

@EnableHystrixDashboard
@EnableCircuitBreaker

@ServletComponentScan(value = { "com.hsd.util.filter" })
@Slf4j
public class HSDEurekaServer {

	public static void main(String[] args) {
		SpringApplication.run(HSDEurekaServer.class, args);
		log.info("Application is success!");
	}

}
