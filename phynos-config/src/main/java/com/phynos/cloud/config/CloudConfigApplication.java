package com.phynos.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author by lupc
 * @date 2020-04-13 11:15
 */
@EnableConfigServer
@SpringCloudApplication
public class CloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication.class, args);
    }

}