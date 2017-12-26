package com.hsd;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */

/**
 * Class Description
 *
 * @author Ford.CHEN
 * @version Sep 1, 20172:16:35 PM
 */
@EnableAdminServer
@SpringCloudApplication

@EnableHystrixDashboard
@EnableCircuitBreaker
public class HsdAdminServer {

    /**
     * Method Description
     *
     * @param args
     * @version Sep 1, 20172:16:35 PM
     * @author Ford.CHEN
     */
    public static void main(String[] args) {

        new SpringApplicationBuilder(HsdAdminServer.class).web(true).run(args);
    }

}
