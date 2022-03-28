package com.menu_project.menuservice.service;


import com.menu_project.menuservice.dto.MenuListDto;
import com.menu_project.menuservice.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;



@RequiredArgsConstructor
@Service
public class MenuService {
        private final MenuRepository menuRepository;

        // transactional readOnly option
        // 트랜잭션 범위는 유지하되, 조회기능만 남겨둠
        // 조회 속도가 개선 -> 등록, 수정, 삭제 기능이 없는 서비스 메소드에 사용추천(메뉴 등록,수정,삭제는 해당 서비스에서 구현X)
        // 읽기 전용
        @Transactional(readOnly = true) // 읽기 전용
        public List<MenuListDto> findAllDesc() {
                return menuRepository.findAllDesc().stream()
                        // map(menu -> new MenuListDto(menu))
                        // stream,map을 통해 MenuListDto로 변환
                        .map(MenuListDto::new)
                        // list로 반환
                        .collect(Collectors.toList());
        }
}
