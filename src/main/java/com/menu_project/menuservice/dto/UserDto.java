package com.menu_project.menuservice.dto;

import com.menu_project.menuservice.entity.user.User;
import lombok.*;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 11)
    private String userPhone;
    private Set<AuthorityDto> authorityDtoSet;

    public static UserDto from(User user) {
        if(user == null){
            return null;}

        return UserDto.builder()
                .userPhone(user.getUserPhone())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}