package com.menu_project.menuservice.controller;

import com.menu_project.menuservice.dto.UserDto;
import com.menu_project.menuservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMyMemberInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }

}
