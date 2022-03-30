package com.menu_project.menuservice.controller;


import com.menu_project.menuservice.dto.TokenDto;
import com.menu_project.menuservice.dto.UserDto;
import com.menu_project.menuservice.jwt.JwtFilter;
import com.menu_project.menuservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    // 인증서 발급 -> authService.login -> 에러 발생
    @PostMapping("/authentication")
    public ResponseEntity<TokenDto> authorize(@RequestBody UserDto userDto) {
      TokenDto tokenDto = authService.login(userDto);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, tokenDto.getGrantType() + tokenDto.getAccessToken());
      return ResponseEntity.ok(tokenDto);
    }


//    @PostMapping("/authentication")
//    public String authorize(@RequestBody UserRequestDto userRequestDto) {
//        UserRequestDto userDto = new UserRequestDto(userPhone, Authority.ROLE_USER);
//        UsernamePasswordAuthenticationToken authenticationToken = userDto.toAuthentication();
//        //error
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        return "success";
//    }


}
