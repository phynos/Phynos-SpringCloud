package com.phynos.cloud.product.sys;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author by lupc
 * @date 2020-08-26 11:45
 */
@Component
@ConfigurationProperties(prefix = "config.product.sys")
public class SysConfig {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
