package com.hsd.devops;

import com.hsd.devops.config.properties.BeetlProperties;
import com.hsd.devops.config.properties.DevopsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;


//@ComponentScan(basePackages={"com.hsd.devops"})
@EnableFeignClients
@EnableEurekaClient
//@EnableConfigServer
@SpringCloudApplication
public class RdcChatopsConsoleWebApplication extends WebMvcConfigurerAdapter {
    protected final static Logger logger = LoggerFactory.getLogger(RdcChatopsConsoleWebApplication.class);

    @Autowired
    DevopsProperties devopsProperties;

    @Resource
    BeetlProperties beetlProperties;


    public static void main(String[] args) {
        SpringApplication.run(RdcChatopsConsoleWebApplication.class, args);
        logger.info("Application is success!");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        beetlProperties.getResourceAutoCheck();

        if(devopsProperties.getSwaggerOpen()){
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }
    
}
