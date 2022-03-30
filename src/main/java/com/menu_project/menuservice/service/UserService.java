package com.menu_project.menuservice.service;

import com.menu_project.menuservice.dto.UserDto;
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
    public UserDto getMyInfo() {
        // 현재 user 정보를 가져와서, userDto 생성
        return UserDto.from(userRepository.findByUserPhone(SecurityUtil.getCurrentUserPhone().get()));
    }
}
