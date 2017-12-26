package com.hsd.gitlab.systemhook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@ServletComponentScan
//@EnableFeignClients(basePackages = {"com.hsd"})
@EnableFeignClients

@EnableEurekaClient
@SpringCloudApplication

@EnableHystrixDashboard
@EnableCircuitBreaker

@Slf4j
public class GitlabHookWeb {

    public static void main(String[] args) {
        SpringApplication.run(GitlabHookWeb.class, args);
        log.info("Application is success!");
    }
}
