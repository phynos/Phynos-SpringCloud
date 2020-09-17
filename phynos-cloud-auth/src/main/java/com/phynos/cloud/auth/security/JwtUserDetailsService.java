package com.phynos.cloud.auth.security;

import com.phynos.cloud.auth.pojo.AuthUserDetails;
import com.phynos.cloud.auth.pojo.AuthUserPoJo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 用户信息获取
 * @author by lupc
 * @date 2020-08-27 14:02
 */
@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!"admin".equals(username)) {
            throw new UsernameNotFoundException("当前用户不存在");
        }
        return new User("admin", passwordEncoder.encode("123456"),
                new ArrayList<>());
    }

}
