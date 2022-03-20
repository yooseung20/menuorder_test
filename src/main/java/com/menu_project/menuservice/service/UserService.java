package com.menu_project.menuservice.service;

import com.menu_project.menuservice.dto.UserDto;
import com.menu_project.menuservice.repository.UserRepository;
import com.menu_project.menuservice.entity.user.Authority;
import com.menu_project.menuservice.entity.user.User;
import com.menu_project.menuservice.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto signup(UserDto userDto) {
        if (userRepository.findByUserPhone(userDto.getUserPhone()) != null) {
            return userDto;
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .userPhone(userDto.getUserPhone())
                .authorities(Collections.singleton(authority))
                .build();

        return UserDto.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String userPhone) {
        return UserDto.from(userRepository.findByUserPhone(userPhone));
    }

    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities() {
        return UserDto.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUserPhone).orElse(null));
    }

    public User findByPhonenumber(String userPhone){
        return userRepository.findByUserPhone(userPhone);
    }

    public User findByUserId(String userId) {
        return userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
    }
}