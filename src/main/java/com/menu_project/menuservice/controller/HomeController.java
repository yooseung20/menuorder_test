package com.menu_project.menuservice.controller;

import com.menu_project.menuservice.config.LoginUser;
import com.menu_project.menuservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final MenuService menuService;


    @GetMapping("/")
    public String home(Model model, @LoginUser ?????){
        model.addAttribute("menu", menuService.findAllDesc());

        // 로그인 성공시 token Dto -> 어디에 저장되는가??? ->  SecurityContext 에 저장
        // 어느 컨트롤러든지 @LoginUser만 사용하면 정보를 가져올 수 있다.-> 어디서?? -> SecurityContext

        // SecurityContext에 저장된 값이 있을 때만 model userPhone으로 등록한다.
        // SecurityContext에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이므로, 로그인 버튼이 보임
        if (user!=null){
            model.addAttribute("userPhone", user.getUserPhone());
        }
        return "home";
    }
}
