package com.phynos.charger.common.jwt;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtTokenProperties.class)
@ConditionalOnProperty(name = "jwt.secret", matchIfMissing = true)
public class JwtTokenAutoConfiguration {

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }


}
