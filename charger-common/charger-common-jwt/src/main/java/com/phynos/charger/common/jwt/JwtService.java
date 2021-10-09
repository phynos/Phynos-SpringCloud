package com.phynos.charger.common.jwt;

import com.phynos.charger.common.jwt.util.Auth0JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    @Autowired
    JwtTokenProperties tokenProperties;

    public String createToken(String username) {
        Map<String,String> claims = new HashMap<>();
        claims.put("username", username);
        return Auth0JwtUtil.create(
                tokenProperties.getSecret(),
                tokenProperties.getExpires(),
                claims);
    }

}
