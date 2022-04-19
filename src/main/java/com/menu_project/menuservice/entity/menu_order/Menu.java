package com.menu_project.menuservice.entity.menu_order;

import lombok.*;
import torder.tabletorder.domain.enums.SaleStatus;
import torder.tabletorder.util.JpaBooleanConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Menu {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    private String category;

    private String foodName;

    private int foodPrice;

    private LocalDateTime createTime = LocalDateTime.now();


    @Builder(builderClassName = "Add", builderMethodName = "Add")
    public Menu(String category, String foodName, int foodPrice) {
        this.category = category;
        this.foodName = foodName;
        this.foodPrice = foodPrice;

    }
    //builderClassName을 사용하지 않으면 여러 @Builder가 생길때 내부클래스들끼리 서로 공유되어서 원하지 않는 필드에 값을 입력할 수 있게된다.

}
