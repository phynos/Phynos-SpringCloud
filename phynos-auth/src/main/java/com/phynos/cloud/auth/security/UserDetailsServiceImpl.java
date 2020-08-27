package com.phynos.cloud.auth.security;

import com.phynos.cloud.auth.pojo.AuthUserDetails;
import com.phynos.cloud.auth.pojo.AuthUserPoJo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 用户信息获取
 * @author by lupc
 * @date 2020-08-27 14:02
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!"admin".equals(username)) {
            throw new UsernameNotFoundException("当前用户不存在");
        }
        AuthUserDetails authUserDetails = new AuthUserDetails(new AuthUserPoJo());
        return authUserDetails;
    }

}
