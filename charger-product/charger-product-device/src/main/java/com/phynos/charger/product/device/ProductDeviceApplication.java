package com.phynos.charger.product.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author by lupc
 * @date 2020-10-09 10:32
 */
@SpringBootApplication
@EnableEurekaClient
public class ProductDeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductDeviceApplication.class, args);
    }

}
