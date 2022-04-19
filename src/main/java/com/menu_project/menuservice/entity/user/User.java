package com.menu_project.menuservice.entity.user;

import com.menu_project.menuservice.util.JpaBooleanConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String phone;

    // 실제 입력받은 값과 db에 저장되는 값을 다르게 설정
    @Convert(converter=JpaBooleanConverter.class)
    private boolean privacyOk;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public User(String phone, boolean privacyOk) {
        this.phone = phone;
        this.privacyOk = privacyOk;
        this.authority = Authority.NOMAL;
    }
}