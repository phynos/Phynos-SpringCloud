package com.phynos.charger.auth.jwttoken.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.phynos.charger.auth.CustomUserDetails;
import com.phynos.charger.auth.jwttoken.vo.JwtAuthVO;

/**
 * JWT管理
 *
 * @author lupc
 * @date 2021/7/1 19:30
 */
public interface TokenService {

    String createJWTToken(String username);

    JwtAuthVO buildAuthResult(String username);

    void refresh(CustomUserDetails loginUser);

    CustomUserDetails getLoginUser(DecodedJWT token);

    DecodedJWT verify(String jwtToken);

}
