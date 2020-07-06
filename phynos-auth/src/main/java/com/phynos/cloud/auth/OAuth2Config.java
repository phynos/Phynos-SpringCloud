package com.phynos.cloud.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

/**
 * @author by lupc
 * @date 2020-07-06 17:28
 */
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
    }
}
