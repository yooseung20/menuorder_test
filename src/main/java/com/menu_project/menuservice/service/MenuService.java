package com.menu_project.menuservice.service;

import com.menu_project.menuservice.entity.menu_order.Menu;
import com.menu_project.menuservice.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    // 모든 메뉴 가져오기
    public void getAllMenus() {
        List<Menu> allMenu = menuRepository.findAllByOrderByCategoryAsc();

    }

}
