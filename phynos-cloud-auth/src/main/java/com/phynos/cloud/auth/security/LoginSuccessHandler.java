package com.phynos.cloud.auth.security;

import com.phynos.cloud.auth.pojo.AuthUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理器
 *
 * @author by lupc
 * @date 2020-08-27 14:04
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //从内存中获取当前认证用户信息
        AuthUserDetails authUserDetails = (AuthUserDetails) authentication.getPrincipal();

        //创建token

        //返回数据

    }

}
