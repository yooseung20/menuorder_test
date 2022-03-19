package com.menu_project.menuservice.entity.menu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="menu")
@Getter
@Setter
@ToString
public class Menu {

    @Id
    @Column(name="menu_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 메뉴 코드

    @Column(nullable=false, length = 50)
    private String menuNm; // 메뉴 이름

    @Column(name="price", nullable = false)
    private int price; // 메뉴 가격

    @Enumerated(EnumType.STRING)
    private MenuSellStatus menuSellStatus; // 상품 판매 상태

}
