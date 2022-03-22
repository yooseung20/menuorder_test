package com.menu_project.menuservice.service;

import com.menu_project.menuservice.dto.UserDto;
import com.menu_project.menuservice.repository.UserRepository;
import com.menu_project.menuservice.entity.user.User;
import com.menu_project.menuservice.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto login(UserDto userDto) {
        // DB에 사용자가 있으면, 사용자 return
        if (userRepository.findByPhonenumber(userDto.getUserPhone()) != null) {
            return userDto;
        }
        // DB에 사용자가 없으면, DB에 저장후 Return
        User user = User.builder()
                .userPhone(userDto.getUserPhone())
                .authority(userDto.getAuthority())
                .build();

        return UserDto.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String userPhone) {
        return UserDto.from(userRepository.findByPhonenumber(userPhone));
    }

    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities() {
        return UserDto.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null));
    }
}