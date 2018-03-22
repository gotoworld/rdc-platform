package com.hsd;

import de.codecentric.boot.admin.notify.LoggingNotifier;
import de.codecentric.boot.admin.notify.Notifier;
import de.codecentric.boot.admin.notify.RemindingNotifier;
import de.codecentric.boot.admin.notify.filter.FilteringNotifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

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
@EnableTurbine
public class HsdMonitorServer {

    /**
     * Method Description
     *
     * @param args
     * @version Sep 1, 20172:16:35 PM
     * @author Ford.CHEN
     */
    public static void main(String[] args) {

        new SpringApplicationBuilder(HsdMonitorServer.class).web(true).run(args);
    }


/*    @Configuration
    public static class NotifierConfig {
        @Bean
        @Primary
        public RemindingNotifier remindingNotifier() {
            RemindingNotifier notifier = new RemindingNotifier(filteringNotifier(loggerNotifier()));
            notifier.setReminderPeriod(TimeUnit.SECONDS.toMillis(10));
            return notifier;
        }

        @Scheduled(fixedRate = 1_000L)
        public void remind() {
            remindingNotifier().sendReminders();
        }

        @Bean
        public FilteringNotifier filteringNotifier(Notifier delegate) {
            return new FilteringNotifier(delegate);
        }

        @Bean
        public LoggingNotifier loggerNotifier() {
            return new LoggingNotifier();
        }
    }*/
}
