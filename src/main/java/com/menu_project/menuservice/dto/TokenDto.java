package com.menu_project.menuservice.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class TokenDto {

    private String grantType;
    private String accessToken;
    private Long accessTokenExpiresIn;
}
