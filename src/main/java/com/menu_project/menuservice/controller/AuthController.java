package com.menu_project.menuservice.controller;

import com.menu_project.menuservice.config.LoginUser;
import com.menu_project.menuservice.dto.TokenDto;
import com.menu_project.menuservice.dto.UserRequestDto;
import com.menu_project.menuservice.entity.user.Authority;
import com.menu_project.menuservice.service.AuthService;
import com.menu_project.menuservice.vo.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // 인증서 발급 -> authService.login -> 에러 발생
//    @PostMapping("/authentication")
//    public ResponseEntity<TokenDto> authorize(@RequestBody UserRequestDto userDto) {
//        TokenDto tokenDto = authService.login(userDto);
//        return ResponseEntity.ok(tokenDto);
//        }


    @PostMapping("/authentication")
    public String authorize(@RequestBody UserRequestDto userDto) {
        //ok -> error
        //UsernamePasswordAuthenticationToken authenticationToken = userDto.toAuthentication();
        //error
        //Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return "success";
    }


}
