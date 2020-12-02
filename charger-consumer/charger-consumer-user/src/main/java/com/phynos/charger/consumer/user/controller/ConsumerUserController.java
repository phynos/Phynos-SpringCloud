package com.phynos.charger.consumer.user.controller;

import com.phynos.charger.consumer.user.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
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

    @Autowired
    ServerProperties serverProperties;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        String port = serverProperties.getPort().toString();
        String product = helloRemote.hello(name);
        String result = String.format("consumer:port=%s;product:%s", port, product);
        return result;
    }

}
