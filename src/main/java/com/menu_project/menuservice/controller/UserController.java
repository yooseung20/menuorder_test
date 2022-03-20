package com.menu_project.menuservice.controller;

import com.menu_project.menuservice.dto.UserDto;
import com.menu_project.menuservice.entity.user.User;
import com.menu_project.menuservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

   /* @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }*/


    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/userPhone")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{userPhone}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String userPhone) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(userPhone));
    }

    /*@PostMapping("/login")
    // 휴대폰 번호를 받음
    public ResponseEntity<String> login(String userPhone, HttpServletRequest request) {
        // 휴대폰 번호로 가입된 계정이 있는 지 확인
        User user = userService.findByPhonenumber(userPhone);
        // 계정이 존재할 때,
        if (user != null) {
            request.getSession().setAttribute("user", user);
        } else {
            // userDto를 userService에 넘겨줘야함
            UserDto userdto = UserDto(userPhone);
            User saveUser = userService.signup(userdto);
            request.getSession().setAttribute("user", saveUser);
        }
        return ResponseEntity.ok(userPhone);
    }*/
}



