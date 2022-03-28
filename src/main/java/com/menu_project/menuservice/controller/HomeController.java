package com.menu_project.menuservice.controller;

import com.menu_project.menuservice.config.LoginUser;
import com.menu_project.menuservice.service.MenuService;
import com.menu_project.menuservice.vo.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final MenuService menuService;


    @GetMapping("/") // @LoginUser 수정하기
    public String home(Model model, @LoginUser CustomUserDetails user){
        model.addAttribute("menu", menuService.findAllDesc());


        // SecurityContext에 저장된 값이 있을 때만 model userPhone으로 등록한다.
        // SecurityContext에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이므로, 로그인 버튼이 보임
        if (user!=null){
            model.addAttribute("userPhone", user.getUsername()); // userPhone을 내뱉도록 override
        }

        return "home";
    }

}
