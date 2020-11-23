package com.phynos.charger.product.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author by lupc
 * @date 2020-10-10 9:58
 */
@SpringBootApplication
@EnableEurekaClient
public class ProductReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductReportApplication.class, args);
    }

}
