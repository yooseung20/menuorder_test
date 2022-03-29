package com.menu_project.menuservice.service;

import com.menu_project.menuservice.dto.UserRequestDto;
import com.menu_project.menuservice.dto.UserResponseDto;
import com.menu_project.menuservice.entity.user.Authority;
import com.menu_project.menuservice.entity.user.User;
import com.menu_project.menuservice.repository.UserRepository;
import com.menu_project.menuservice.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponseDto getMyInfo() {
        // 현재 user 정보를 가져와서, userResponse 생성
        return userRepository.findByUserPhone(SecurityUtil.getCurrentUserPhone().get())
                .map(UserResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }
}
