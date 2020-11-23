package com.phynos.charger.consumer.user.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author by lupc
 * @date 2020-06-09 11:55
 */
@FeignClient(name= "spring-cloud-product-user")
public interface HelloRemote {

    @RequestMapping(value = "/hello")
    String hello(@RequestParam(value = "name") String name);

}
