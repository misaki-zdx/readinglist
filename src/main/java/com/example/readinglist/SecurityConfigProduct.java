package com.example.readinglist;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Misaki
 * @date 2019/3/1/001 14:29
 **/

@Profile("product")
@Configuration
@EnableWebSecurity
public class SecurityConfigProduct extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置不需要登陆
        http.authorizeRequests().anyRequest().permitAll().and().logout().permitAll();
    }
}
