package com.menu_project.menuservice.entity.menu_order;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "menu_id")
    private long id;
    private String category;
    private String foodName;
    private int foodPrice;

    public Menu(long id, int foodPrice){
        this.id = id;
        this.foodPrice = foodPrice;
    }

    @Builder(builderClassName ="Add")
    public Menu(String category, String foodName, int foodPrice) {
        this.category = category;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }


}
