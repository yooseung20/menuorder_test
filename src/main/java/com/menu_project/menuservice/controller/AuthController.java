package com.menu_project.menuservice.controller;

import com.menu_project.menuservice.dto.LoginDto;
import com.menu_project.menuservice.dto.TokenDto;
import com.menu_project.menuservice.entity.user.User;
import com.menu_project.menuservice.jwt.JwtFilter;
import com.menu_project.menuservice.jwt.TokenProvider;
import com.menu_project.menuservice.service.UserService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    
    private final UserService userService;
    private final TokenProvider tokenProvider;

    public AuthController(UserService userService, TokenProvider tokenProvider){
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    //토큰 발급.
    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        User user = userService.findByPhonenumber(loginDto.getUserPhone());
        //유저정보 찾기

        String jwt = tokenProvider.createToken(user);
        //찾은 유저정보로 토큰을 생성

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        //헤더에 토큰을 담아서

        //리턴
        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
