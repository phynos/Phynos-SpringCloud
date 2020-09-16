package com.phynos.cloud.consumer.user.controller;

import com.phynos.cloud.consumer.user.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by lupc
 * @date 2020-06-09 11:54
 */
@RestController
public class ConsumerUserController {

    @Autowired
    HelloRemote helloRemote;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return helloRemote.hello(name);
    }

}
