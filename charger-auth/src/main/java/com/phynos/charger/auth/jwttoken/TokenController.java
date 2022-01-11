package com.phynos.charger.auth.jwttoken;

import com.phynos.charger.auth.UserAuthService;
import com.phynos.charger.auth.jwttoken.dto.LoginDTO;
import com.phynos.charger.auth.jwttoken.service.TokenService;
import com.phynos.charger.auth.jwttoken.vo.JwtAuthVO;
import com.phynos.charger.auth.kaptcha.KaptchaService;
import com.phynos.charger.common.utils.R;
import com.phynos.charger.common.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 认证接口
 *
 * @author lupc
 * @date 2021/7/1 19:29
 */
@RestController
@RequestMapping("/auth/token")
public class TokenController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;
    @Autowired
    KaptchaService kaptchaService;
    @Autowired
    UserAuthService userAuthService;

    @PostMapping("")
    public R<JwtAuthVO> tokenAuth(@RequestBody @Valid LoginDTO dto) throws AuthenticationException {
        //验证码
        ResultCodeEnum result = kaptchaService.valid(dto.getCode());
        if (result != ResultCodeEnum.OK) {
            return R.error(result);
        }
        UsernamePasswordAuthenticationToken upToken = userAuthService.createUpAuthTokenBeforAuth(
                dto.getUsername(),
                dto.getPassword());
        try {
            // Perform the security
            final Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //登录成功
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            //登录失败
            return R.error(ResultCodeEnum.USERNAME_PASSWORD_MISMATCH);
        } catch (AccountExpiredException e) {
            return R.error(ResultCodeEnum.USERNAME_PASSWORD_MISMATCH, "账户被禁用");
        } catch (LockedException e) {
            return R.error(ResultCodeEnum.USERNAME_PASSWORD_MISMATCH, "账户被锁定");
        } catch (CredentialsExpiredException e) {
            return R.error(ResultCodeEnum.USERNAME_PASSWORD_MISMATCH, "账户凭证过期");
        } catch (SessionAuthenticationException e) {
            return R.error(ResultCodeEnum.USERNAME_PASSWORD_MISMATCH, "您已在其他设备登录，禁止登录");
        }
        //返回TOKEN给客户端
        return R.data(tokenService.buildAuthResult(dto.getUsername()));
    }


}
