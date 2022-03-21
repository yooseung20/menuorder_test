package com.menu_project.menuservice.service;

import com.menu_project.menuservice.entity.user.User;
import com.menu_project.menuservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userPhone) throws UsernameNotFoundException {
        return createUserDetails (userRepository.findByPhonenumber(userPhone));
    }
    // phonenumber -> user(entity) -> UserDetail.User로 return 해야하는데,
    // user(entity) -> UserDetail.User (설계가 달라서 어떻게 해야할지 모르겠음.)

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(User user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAuthority().toString());

        return new org.springframework.security.core.userdetails.User(
                user.getUserPhone(), // id -> phonenumber
                user.getUserPhone(), // password -> phonenumber
                Collections.singleton(grantedAuthority)
        );
    }

}
