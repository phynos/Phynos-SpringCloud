package com.phynos.cloud.product.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author by lupc
 * @date 2020-06-09 12:01
 */
@SpringBootApplication
@EnableEurekaClient
public class ProductUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductUserApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
