package com.menu_project.menuservice.service;

import com.menu_project.menuservice.dto.UserRequestDto;
import com.menu_project.menuservice.entity.user.Authority;
import com.menu_project.menuservice.entity.user.User;
import com.menu_project.menuservice.repository.UserRepository;
import com.menu_project.menuservice.vo.CustomUserDetails;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    // loadUserByUsername(userPhone) -> 휴대폰 번호로 유저정보 확인
    // 유저 정보가 없으면, db save
    public CustomUserDetails loadUserByUsername(String userPhone) {
        User user = userRepository.findByUserPhone(userPhone).get();
        if (user == null){
            userRepository.save(new UserRequestDto(userPhone, Authority.ROLE_USER).toEntity());
            user = userRepository.findByUserPhone(userPhone).get();
        }
        return createUserDetails(user);

    }

    // DB 에 User 값이 존재한다면(존재하지 않은 경우 x -> 정보 없으면 db 저장) UserDetails 객체로 만들어서 리턴
    private CustomUserDetails createUserDetails(User user) {

        CustomUserDetails userDetail = new CustomUserDetails();

        userDetail.setUserphone(user.getUserPhone());
        userDetail.setAuthorities(getAuthorities(user.getUserPhone()));

        return userDetail;
    }

    public Collection<GrantedAuthority> getAuthorities(String userPhone) {
        User user = userRepository.findByUserPhone(userPhone).get();
        Authority auth = user.getAuthority();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(auth.toString()));
        return authorities;
    }



}
