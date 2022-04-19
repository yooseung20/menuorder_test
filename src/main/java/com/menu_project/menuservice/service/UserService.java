package com.menu_project.menuservice.service;

import com.menu_project.menuservice.dto.UserDto;
import com.menu_project.menuservice.entity.user.User;
import com.menu_project.menuservice.repository.UserRepository;
import com.menu_project.menuservice.util.error_Exception.CustomException;
import com.menu_project.menuservice.util.error_Exception.ErrorCode;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 로그인 처리하고 -> userId를 넘겨준다.
    public Long register(UserDto.Register userDto){
        if(userDto.isPrivacyOk() == false){
            throw new CustomException(ErrorCode.PRIVACY_AGREE_FALSE);
        }

        // Optional<User> -> none 값이 아니라면 중복 에러
        // 값이 있는 경우에 이를 사용하고 없는 경우에 아무 동작도 하지 않는다면, Optional.ifPresent()를 활용
        userRepository.findByPhone(userDto.getPhone()).ifPresent((user) -> {
            throw new CustomException(ErrorCode.DUPLICATED_MEMBER);
        });

        User user = userRepository.save(userDto.toEntity());
        return user.getId();
    }

    public UserDto.UserInfo sessionLogin(UserDto.Login userDto, HttpSession session){

        User user = userRepository.findByPhone(userDto.getPhone()).get();
        if (user == null){
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUNDED);
        }

        // session에 user 저장
        session.setAttribute("LOGIN_USER", user);

        return new UserDto.UserInfo(user);



    }
}
