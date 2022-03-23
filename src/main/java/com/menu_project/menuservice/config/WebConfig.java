package com.menu_project.menuservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor

// 설정파일을 만들기 위한 애노테이션 or Bean을 등록하기 위한 애노테이션
@Configuration
// @EnableWebMvc가 자동적으로 세팅해주는 설정에 원하는 설정 추가 가능
// 즉 Override가 가능하다.
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }
}