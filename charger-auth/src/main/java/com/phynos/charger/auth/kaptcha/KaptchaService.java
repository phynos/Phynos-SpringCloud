package com.phynos.charger.auth.kaptcha;


import com.phynos.charger.common.utils.ResultCodeEnum;

import javax.servlet.http.HttpServletResponse;

public interface KaptchaService {

    void kaptcha(HttpServletResponse response);

    ResultCodeEnum valid(String code);

}
