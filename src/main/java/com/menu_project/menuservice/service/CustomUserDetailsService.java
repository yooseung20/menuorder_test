package com.menu_project.menuservice.service;


import com.menu_project.menuservice.entity.user.User;
import com.menu_project.menuservice.repository.UserRepository;
import com.menu_project.menuservice.vo.CustomUserDetails;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    // loadUserByUsername(userPhone) -> 휴대폰 번호로 유저정보 확인
    public CustomUserDetails loadUserByUsername(String userPhone) throws UsernameNotFoundException {
        return createUserDetails (userRepository.findByPhonenumber(userPhone));
    }

    // phonenumber -> user(entity) -> CustomUserDetail로 return 해야하는데,
    // -> userPhone 정보를 넘기도록 설정

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private CustomUserDetails createUserDetails(User user) {

        CustomUserDetails userDetail = new CustomUserDetails();

        userDetail.setUSERPHONE(user.getUserPhone());
        userDetail.setAUTHORITY(user.getAuthority().toString());

        return userDetail;
    }

}
