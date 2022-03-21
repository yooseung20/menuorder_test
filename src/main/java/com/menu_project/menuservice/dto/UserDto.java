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
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 11)
    private String userPhone;
    private Authority authority;

    public static UserDto from(User user) {
        if(user == null){
            return null;}

        return UserDto.builder()
                .userPhone(user.getUserPhone())
                .authority(user.getAuthority())
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(userPhone);
    }


}