package com.menu_project.menuservice.controller;

import com.menu_project.menuservice.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    @GetMapping("/menu")
    public ResponseEntity getMenuList(){
        //어떤 타입으로 받게 될지 지정하지 못함
        return ResponseEntity.status(HttpStatus.OK).body("how to...");
    }

}
