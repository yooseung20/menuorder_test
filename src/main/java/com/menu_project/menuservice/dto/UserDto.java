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
public class UserDto {

    @NotNull
    @Size(min = 11)
    private String userPhone;


   @Builder
   public UserDto(String userPhone){
       this.userPhone = userPhone;

   }
   public User toEntity(){
       return User.builder()
               .userPhone(userPhone)
               .authority(Authority.ROLE_USER)
               .build();
   }


    public static UserDto from(User user) {
       return new UserDto(user.getUserPhone());
    }


    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(userPhone, userPhone);
    }



}