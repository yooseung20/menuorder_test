package com.menu_project.menuservice.controller;

import com.menu_project.menuservice.config.LoginUser;
import com.menu_project.menuservice.dto.TokenDto;
import com.menu_project.menuservice.dto.UserRequestDto;
import com.menu_project.menuservice.service.AuthService;
import com.menu_project.menuservice.vo.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    // 인증서 발급
    @PostMapping("/authentication")
    public ResponseEntity<TokenDto> authorize(@RequestBody UserRequestDto userDto) {
        TokenDto tokenDto = authService.login(userDto);
        return ResponseEntity.ok(tokenDto);
    }



}
