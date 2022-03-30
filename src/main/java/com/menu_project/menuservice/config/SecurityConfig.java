package com.menu_project.menuservice.config;


import com.menu_project.menuservice.jwt.*;
import com.menu_project.menuservice.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 활성화
// @PreAuthorize 어노테이션을 메소드 단위로 추가하기 위한 목적
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정페이지에서 특정권한이 있는 유저만 접근가능하게 설정 -> 추후 사용예정
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomUserDetailsService customUserDetailsService;


    // h2 database 테스트가 원활하도록 관련 API 들은 전부 무시
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/h2-console/**"
                        , "/favicon.ico"
                        , "/error"
                        , "/css/**", "/images/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf를 disable
                .csrf().disable()
                .httpBasic().disable() // security에서 기본 제공하는 login 페이지 이용안함
                // exceptionHandling -> 만들어준 클래스 추가
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                //h-2 console 사용을 위한 설정 추가
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 STATELESS 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                // token을 받기위한 api
                .antMatchers("/authentication").permitAll()
                // login(=signup)
                .antMatchers("/login").permitAll()
                .antMatchers("/cart", "/order", "/receipt").permitAll()
                .anyRequest().authenticated() //그외 나머지 요청은 모두 인증된 회원만 접근 가능

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }
}
