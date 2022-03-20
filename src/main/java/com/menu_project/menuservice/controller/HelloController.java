package com.menu_project.menuservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@Slf4j
public class HelloController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello(@AuthenticationPrincipal Principal authentication){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info(SecurityContextHolder.getContext().getAuthentication().toString());
        log.info(authentication.toString());
        return ResponseEntity.ok("hello");
    }
}
