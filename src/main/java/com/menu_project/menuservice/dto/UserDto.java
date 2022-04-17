package com.menu_project.menuservice.dto;

import com.menu_project.menuservice.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class UserDto {

    public static final String phoneRegexp = "^\\d{3}-\\d{4}-\\d{4}$";

    // 회원가입시 만들어줄 Dto
    @Getter
    public static class Register{
        @Pattern(regexp = phoneRegexp, message ="잘못된 휴대폰번호입니다.")
        private String phone;

        @NotNull
        private boolean privacyOk;

        //개인정보 확인 동의 및 가입버튼 : privacyOk -> true
        public Register(String phone, boolean privacyOk){
            this.phone = phone;
            this.privacyOk = privacyOk;
        }

        @Builder
        public User toEntity(){
            return User.builder()
                    .phone(phone)
                    .privacyOk(privacyOk)
                    .build();
        }
    }

    // 로그인을 시도할 때 만들어줄 Dto
    @Getter
    @NoArgsConstructor
    public static class Login{
        @Pattern(regexp = phoneRegexp, message = "잘못된 휴대폰번호입니다.")
        private String phone;

        public Login(String phone) {
            this.phone = phone;
        }
    }

    // 유저 정보를 담을 Dto-> 휴대폰 번호와 Authority(ROLE) 정보를 담고 있음
    @Getter
    @NoArgsConstructor
    public static class UserInfo{


        private String phone;
        private String authority;

        public UserInfo(User user) {
            this.phone = user.getPhone();
            this.authority = user.getAuthority().getValue();
        }


    }


}