package com.phynos.charger.auth.config;

import com.phynos.charger.auth.security.JwtUserDetailsService;
import com.phynos.charger.auth.filter.JwtAuthenticationEntryPoint;
import com.phynos.charger.auth.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SpringSecurity核心配置类
 *
 * @author by lupc
 * @date 2020-07-06 17:31
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);

        web.ignoring().antMatchers("/actuator/**");
        web.ignoring().antMatchers("/instances");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests().antMatchers("/auth/token")
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .anyRequest().authenticated().and()
                //确保是无状态的session
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //增加过滤器校验每个请求的token
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 测试可以使用简单的将用户写入内存
     *
     * @param auth
     * @throws Exception
     */
    private void inMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中设定几个用户和角色
        auth.inMemoryAuthentication()
                .withUser("lupc")
                .password(passwordEncoder.encode("123456"))
                .roles("USER")
                .and()
                .withUser("phynos")
                //spring5 密码强制使用加密
                .password(passwordEncoder.encode("123456"))
                .roles("USER", "ADMIN");
    }

}
