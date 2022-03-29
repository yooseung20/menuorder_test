package com.menu_project.menuservice.vo;

import com.menu_project.menuservice.entity.user.Authority;

import com.menu_project.menuservice.entity.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {


    private String userphone;
    private Collection<? extends GrantedAuthority> authorities;


    public CustomUserDetails(String userphone, Collection<? extends GrantedAuthority> authorities) {

        this.userphone = userphone;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
    }

    @Override
    public String getUsername() {
        return userphone;
    }

    @Override
    public String getPassword() {
        return userphone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}