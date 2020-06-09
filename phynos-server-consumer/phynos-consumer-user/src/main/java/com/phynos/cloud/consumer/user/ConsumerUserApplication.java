package com.phynos.cloud.consumer.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author by lupc
 * @date 2020-06-08 16:30
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
public class ConsumerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserApplication.class);
    }

}
