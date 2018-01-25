package com.hsd.gitlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableEurekaClient
//@EnableConfigServer
@SpringCloudApplication

@EnableFeignClients
//@EnableFeignClients(basePackages = {"com.hsd"})

@EnableHystrixDashboard
@EnableCircuitBreaker

@Slf4j
public class GitlabHooksServer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GitlabHooksServer.class).web(true).run(args);
        log.info("Application is success!");

    }
}

