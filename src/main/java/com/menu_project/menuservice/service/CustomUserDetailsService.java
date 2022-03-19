package com.menu_project.menuservice.service;

import com.menu_project.menuservice.repository.UserRepository;
import com.menu_project.menuservice.entity.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;

import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public User loadUserByUserPhone(final String userPhone) {
        return userRepository.findOneWithAuthoritiesByUserPhone(userPhone)
                .map(user -> createUser(userPhone, user))
                .orElseThrow(() -> new UsernameNotFoundException(userPhone + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private User createUser(String userPhone, User user) {
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return user;
        // USER에 grantedAuthority를 넣어준 객체를 반환해야함
        // 기존 userdetails.User은 우리가 사용하는 user와 형태가 다름
    }
}
