package com.phynos.charger.auth.jwttoken.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.phynos.charger.auth.CustomUserDetails;
import com.phynos.charger.auth.UserAuthService;
import com.phynos.charger.auth.jwttoken.JwtTokenProperties;
import com.phynos.charger.auth.jwttoken.service.TokenService;
import com.phynos.charger.auth.jwttoken.util.Auth0JwtUtil;
import com.phynos.charger.auth.jwttoken.vo.JwtAuthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2021/7/1 19:30
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    JwtTokenProperties jwtTokenProperties;
    @Autowired
    UserAuthService userAuthService;

    @Override
    public String createJWTToken(String username) {
        Map<String, String> claims = new HashMap<>();
        claims.put("username", username);
        return Auth0JwtUtil.create(
                jwtTokenProperties.getSecret(),
                jwtTokenProperties.getExpires(),
                claims);
    }

    @Override
    public JwtAuthVO buildAuthResult(String username) {
        String token = createJWTToken(username);
        JwtAuthVO vo = new JwtAuthVO();
        vo.setAccessToken(token);
        vo.setExpiresIn(LocalDateTime.now().plusDays(jwtTokenProperties.getExpires()));
        vo.setUserType(1);
        return vo;
    }

    @Override
    public void refresh(CustomUserDetails loginUser) {

    }

    @Override
    public CustomUserDetails getLoginUser(DecodedJWT token) {
        String username = token.getClaim("username").asString();
        return userAuthService.getLoginUser(username);
    }

    @Override
    public DecodedJWT verify(String jwtToken) {
        return Auth0JwtUtil.verify(jwtTokenProperties.getSecret(), jwtToken);
    }


}
