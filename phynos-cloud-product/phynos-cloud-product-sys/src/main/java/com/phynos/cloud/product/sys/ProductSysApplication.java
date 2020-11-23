package com.phynos.charger.product.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author by lupc
 * @date 2020-08-21 10:28
 */
@SpringBootApplication
@EnableEurekaClient
public class ProductSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductSysApplication.class, args);
    }

}
