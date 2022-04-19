package com.menu_project.menuservice.entity.menu_order;

import com.menu_project.menuservice.entity.user.User;

import javax.persistence.*;
import java.util.List;


@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orderList;

    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user; // 비회원인 경우 , null 값이 들어감

}
