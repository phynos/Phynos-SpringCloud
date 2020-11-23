package com.phynos.charger.product.user.controller;

import com.phynos.charger.product.user.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by lupc
 * @date 2020-06-09 14:05
 */
@RestController
public class HelloController {

    @Autowired
    UserConfig userConfig;

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        String rep = String.format("hello %s, your password: %s" , name, userConfig.getPassword());
        return rep;
    }

}