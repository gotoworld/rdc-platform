package com.hsd.devops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//import com.hsd.devops.config.properties.DevopsProperties;


@EnableFeignClients
@EnableEurekaClient
//@EnableConfigServer
@SpringCloudApplication
public class DevOpsConsoleApplication extends WebMvcConfigurerAdapter{

    protected final static Logger logger = LoggerFactory.getLogger(DevOpsConsoleApplication.class);

//    @Autowired
//    DevopsProperties devopsProperties;

    
    public static void main(String[] args) {
        SpringApplication.run(DevOpsConsoleApplication.class, args);
        logger.info("Application is success!");
    }
    
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        if(devopsProperties.getSwaggerOpen()){
//            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        }
//    }
    
}
