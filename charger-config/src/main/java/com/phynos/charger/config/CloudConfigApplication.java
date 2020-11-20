package com.phynos.charger.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author by lupc
 * @date 2020-04-13 11:15
 */
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class CloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication.class, args);
    }

}
