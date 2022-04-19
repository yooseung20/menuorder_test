package com.menu_project.menuservice.entity.menu_order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderMenu> orderMenuList = new ArrayList<>();

    private LocalDateTime createTime = LocalDateTime.now();

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private int orderPrice;

    public Order() {
    }

    //Order생성
    //List<OrderMenu>에 Order추가
    public static Order createOrder(List<OrderMenu> orderMenuList){
        Order order = new Order();
        int orderPrice = 0;
        for (OrderMenu orderMenu : orderMenuList) {
            orderMenu.setOrder(order);

            int menuPrice = orderMenu.getMenu().getPrice();
            int menuCount = orderMenu.getCount();
            orderPrice = orderPrice + (menuPrice * menuCount);
        }
        order.orderPrice = orderPrice;
        order.orderMenuList = orderMenuList;
        return order;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderMenuList=" + orderMenuList +
                ", createTime=" + createTime +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
