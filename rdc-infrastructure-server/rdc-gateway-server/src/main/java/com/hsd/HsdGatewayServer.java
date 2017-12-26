package com.hsd;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@EnableZuulProxy
@SpringCloudApplication

@EnableHystrixDashboard
@EnableCircuitBreaker
public class HsdGatewayServer {
    
    public static void main(String[] args) {
        new SpringApplicationBuilder(HsdGatewayServer.class).web(true).run(args);
    }
    
    /*
     * @Bean public ThrowExceptionFilter throwExceptionFilter(){ return new ThrowExceptionFilter(); }
     * @Bean public AccessFilter accessFilter() { return new AccessFilter(); }
     */
    
}
