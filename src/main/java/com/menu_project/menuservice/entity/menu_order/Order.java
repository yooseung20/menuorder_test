package com.menu_project.menuservice.entity.menu_order;


import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private long id;

    private int orderPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderMenu> orderMenuList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="payment_id")
    private Payment payment;

    private LocalDateTime localDataTime;

    public Order() {}

    //order 만들기
    public static Order createOrder(List<OrderMenu> orderMenuList){
        Order newOrder = new Order();
        int orderPrice = 0;

        for (OrderMenu orderMenu : orderMenuList){
            int foodPrice = orderMenu.getMenu().getFoodPrice();
            int foodAmount = orderMenu.getFoodAmount();
            orderPrice += foodPrice * foodAmount;
            newOrder.setOrderPrice(orderPrice);
            newOrder.orderMenuList = orderMenuList;

        }
        return newOrder;
    }


}
