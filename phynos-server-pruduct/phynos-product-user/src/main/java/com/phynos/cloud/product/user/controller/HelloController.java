package com.phynos.cloud.product.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by lupc
 * @date 2020-06-09 14:05
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello " + name + "，this is first messge";
    }

}