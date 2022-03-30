package com.menu_project.menuservice.service;

import com.menu_project.menuservice.dto.TokenDto;
import com.menu_project.menuservice.dto.UserDto;
import com.menu_project.menuservice.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Transactional
    public TokenDto login(UserDto userDto) {
        // 1. Login Phonenumber 를 기반으로 AuthenticationToken 생성
        // email, password 대신 phonenumber -> UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken = userDto.toAuthentication();
        System.out.println("/////////authenticationToken 완성///////////"); // -> 정상적으로 만들어짐.
        System.out.println("/////////" + authenticationToken.getPrincipal() + "/////////"); // -> userPhone
        
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println("/////////Authentication 완성/////////"); // -> 실패

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);


        // 3. 토큰 발급
        return tokenDto;
    }
}