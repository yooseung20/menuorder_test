package com.menu_project.menuservice.entity.menu_order;

import com.menu_project.menuservice.dto.CartDto;
import com.menu_project.menuservice.dto.MenuDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordermenu_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id") // 매핑할 외래키 지정
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id") // 매핑할 외래키 지정
    private Menu menu;

    private int foodAmount;

    public OrderMenu(Menu menu , int foodAmount){
        this.menu = menu;
        this.foodAmount = foodAmount;
    }

    // Order에 추가할 OrderMenu 만들기 (List<OrderMenu>)
    public static List<OrderMenu> createOrderMenuList(CartDto.CartList cartList){
        List<MenuDto.CartMenu> cartMenuList = cartList.getCartList();
        List<OrderMenu> orderMenuList = new ArrayList<>();

        for(int i=0; i < cartMenuList.size(); i++){
            Menu menu = cartMenuList.get(i).getMenu();
            int foodAmount = cartMenuList.get(i).getFoodAmount()

            OrderMenu orderMenu = new OrderMenu(menu, foodAmount);
            orderMenuList.add(orderMenu);
        }

        return orderMenuList;
    }




}
