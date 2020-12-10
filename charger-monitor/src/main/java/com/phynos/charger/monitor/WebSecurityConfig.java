package com.phynos.charger.monitor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final String contextPath;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    public WebSecurityConfig(ServerProperties serverProperties) {
        this.contextPath = serverProperties.getServlet().getContextPath();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);

        web.ignoring().antMatchers("/actuator/**");
        web.ignoring().antMatchers("/instances");

        web.ignoring().antMatchers("/assets/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successForwardUrl("/")
                .failureForwardUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> {
                    logger.info("登录验证失败，requestUri={}，loginUrl={}", httpServletRequest.getRequestURI(), "/login");
                    logger.error(e.getMessage(), e);
                    redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/login");
                });
    }

}
