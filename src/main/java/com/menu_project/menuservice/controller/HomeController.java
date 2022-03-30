package com.menu_project.menuservice.controller;

import com.menu_project.menuservice.config.LoginUser;
import com.menu_project.menuservice.dto.TokenDto;
import com.menu_project.menuservice.service.MenuService;
import com.menu_project.menuservice.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final MenuService menuService;

    @GetMapping("/") // @LoginUser 수정하기
    public String home(Model model){
        model.addAttribute("menu", menuService.findAllDesc());

        // SecurityContext 저장된 값이 있을 때만 model userPhone으로 등록한다.
        // SecurityContext -> Authentication

//        String userinfo = SecurityUtil.getCurrentUserPhone().get();

        // model엔 아무런 값이 없는 상태면, 로그인 버튼이 보임
//        if (userinfo!=null){
//           model.addAttribute("userPhone", userinfo);
//        }

        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logout")
    public void logout(){
    }

}
