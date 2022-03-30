package com.menu_project.menuservice.vo;

import com.menu_project.menuservice.entity.user.Authority;


import com.menu_project.menuservice.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@RequiredArgsConstructor
public class CustomUserDetails extends User implements UserDetails {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private static final Log logger = LogFactory.getLog(org.springframework.security.core.userdetails.User.class);

    private String password;

    private String username;

    private String userphone;

    private Collection<? extends GrantedAuthority> authorities;

    private final boolean accountNonExpired = true;

    private final boolean accountNonLocked = true;

    private final boolean credentialsNonExpired = true;

    private final boolean enabled = true;


    public CustomUserDetails(String userphone, Collection<? extends GrantedAuthority> authorities){
        this.username = userphone;
        this.password = userphone;
        this.userphone = userphone;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.authorities;
    }

    @Override
    public String getUsername() {
        return userphone;
    }

    @Override
    public String getPassword() {
        return userphone;
    }


    public String getUserphone() {
        return userphone;
    }




}