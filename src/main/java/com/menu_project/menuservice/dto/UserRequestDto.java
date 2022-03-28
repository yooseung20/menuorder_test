package com.menu_project.menuservice.dto;

import com.menu_project.menuservice.entity.user.Authority;
import com.menu_project.menuservice.entity.user.User;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



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
        return new UsernamePasswordAuthenticationToken(userPhone, userPhone);
    }



}