package com.phynos.charger.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by lupc
 * @date 2020-08-27 15:07
 */
@RestController
public class EmployeeController {

    @RequestMapping({"/greeting"})
    public String welcomePage() {
        return "Welcome!";
    }

}
