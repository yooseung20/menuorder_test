package com.menu_project.menuservice.dto;

import com.menu_project.menuservice.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String userPhone;

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getUserPhone());
    }
}
