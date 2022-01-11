package com.phynos.charger.auth.handler;

import com.phynos.charger.common.utils.JsonUtil;
import com.phynos.charger.common.utils.R;
import com.phynos.charger.common.utils.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("无登陆访问：{}", request.getRequestURI());
        R<?> r = R.code(ResultCodeEnum.UNAUTHORIZED);
        String json = JsonUtil.objectToString(r);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

}
