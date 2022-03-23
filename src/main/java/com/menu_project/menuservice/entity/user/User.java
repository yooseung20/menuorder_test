package com.menu_project.menuservice.entity.user;

import lombok.*;
import javax.persistence.*;


@Entity
@Table(name = "user")
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_phone", length = 11, unique = true)
    private String userPhone;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public User(String userPhone, Authority authority){
        this.userPhone = userPhone;
        this.authority = authority;
    }

}