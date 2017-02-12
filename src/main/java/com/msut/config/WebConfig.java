package com.msut.config;

import com.msut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.*;

/**
 * Created by mariusz on 05.02.17.
 */
@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                    .loginPage("/login.html")
                    .defaultSuccessUrl("/chat.html")
                    .failureHandler((req, res, exception) -> {
                        res.sendRedirect("/login.html?error="+exception.getMessage());
                    })
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/login.html")
                    .permitAll()
                    .and()
                .authorizeRequests()
                    .antMatchers("/src/**", "/node_modules/**").authenticated()
                    .antMatchers("/bower_components/**").permitAll()
                    .antMatchers("/login.html").anonymous()
                    .anyRequest().authenticated();
    }
}
