package com.menu_project.menuservice.dto;

import com.menu_project.menuservice.entity.user.Authority;
import com.menu_project.menuservice.entity.user.User;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserRequestDto {

    @NotNull
    @Size(min = 11)
    private String userPhone;
    private Authority authority;

   @Builder
   public UserRequestDto(String userPhone, Authority authority){
       this.userPhone = userPhone;
       this.authority = authority;
   }
   public User toEntity(){
       return User.builder()
               .userPhone(userPhone)
               .authority(authority)
               .build();
   }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authority.toString()));
        return new UsernamePasswordAuthenticationToken(userPhone, userPhone, authorities);
    }



}