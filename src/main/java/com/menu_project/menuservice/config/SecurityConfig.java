package com.menu_project.menuservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
// Spring Security 설정들을 활성화 시켜준다.
@EnableWebSecurity
// 사용자 지정 자체 보안 구성을 원할 때, WebSecurityConfigurerAdapter 을 상속받아 구현하면 된다.
// 이렇게 하면 기본 자동 구성이 비활성화되고 사용자 지정 보안 구성이 활성화 된다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // h2-console 접근 허용
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 일단 조건에 상관없이 모두 접근할 수 있도록 설정한다. 추후 설정 예정
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();

    }
}

