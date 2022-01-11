package com.phynos.charger.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/1/10 18:06
 */
@RestController
public class LoginController {

    @RequestMapping({"/auth"})
    public String welcomePage() {
        return "Welcome!";
    }

}
