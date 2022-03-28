package com.menu_project.menuservice.controller;

import com.menu_project.menuservice.dto.UserResponseDto;
import com.menu_project.menuservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyMemberInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }
}
