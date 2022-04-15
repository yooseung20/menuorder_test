package com.menu_project.menuservice.controller;


import com.menu_project.menuservice.dto.UserDto;
import com.menu_project.menuservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated UserDto.Register userDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            // 잘못된 input 값을 넣어준 경우 -> BAD_REQUEST
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult);
        }

        // bindingResult 가 error를 가지고 있지 않은 경우 정상적으로 가입처리
        Long userId = userService.register(userDto);
        // 가입 후 userId return
        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }

    // sesion login 처리
    @PostMapping("/login/session")
    public ResponseEntity sessionLogin(@RequestBody @Validated UserDto.Login userDto, BindingResult bindingResult, HttpSession session) {

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult);
        }
        // user의 휴대폰 번호와, Authority 정보를 String으로 받는다.
        UserDto.UserInfo userInfo = userService.sessionLogin(userDto, session);

        return ResponseEntity.status(HttpStatus.OK).body(userInfo);

    }

}
