package com.menu_project.menuservice.service;

import com.menu_project.menuservice.dto.UserDto;
import com.menu_project.menuservice.entity.user.Authority;
import com.menu_project.menuservice.entity.user.User;
import com.menu_project.menuservice.repository.UserRepository;
import com.menu_project.menuservice.vo.CustomUserDetails;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        System.out.println("loadbyuser실행");

        User user = userRepository.findByUserPhone(userPhone);
        System.out.println("userinfo 얻기 시도");

        if (user == null){
            System.out.println("db에 유저 정보 없음");
            UserDto userDto = new UserDto(userPhone);
            System.out.println("UserDto 생성");
            System.out.println(userDto.getUserPhone()); // -> userPhone을 바탕으로 UserDto 생성 불가, 빈 값을 받아옴

            userRepository.save(userDto.toEntity());
            System.out.println("db에 없어서 저장");

            user = userRepository.findByUserPhone(userPhone);
            System.out.println("db에 저장후 얻기");
        }
        return createUserDetails(user);

    }

    // DB 에 User 값이 존재한다면(존재하지 않은 경우 x -> 정보 없으면 db 저장) UserDetails 객체로 만들어서 리턴
    private CustomUserDetails createUserDetails(User user) {
        return new CustomUserDetails(
                user.getUserPhone(),
                getAuthorities(user.getUserPhone())
        );
    }


    public Collection<GrantedAuthority> getAuthorities(String userPhone) {
        User user = userRepository.findByUserPhone(userPhone);
        Authority auth = user.getAuthority();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(auth.toString()));
        return authorities;
    }


}
