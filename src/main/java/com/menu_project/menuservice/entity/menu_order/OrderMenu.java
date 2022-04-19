package com.menu_project.menuservice.entity.menu_order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import torder.tabletorder.domain.dto.BasketDTO;


import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderMenu {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordermenu_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private int count;

    //데이터베이스에서 외래키를 관리하는 쪽이 연관관게의 주인


    public OrderMenu(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

<<<<<<< HEAD
    //Order에 추가할 List<OrderMenu>
    public static List<OrderMenu> createOrderMenuList(List<BasketDTO> basketList){
        List<OrderMenu> orderMenuList = new ArrayList<>();
        for (int i = 0; i < basketList.size(); i++) {
            Menu menu = basketList.get(i).getMenu();
            int count = basketList.get(i).getCount();

            OrderMenu orderMenu = new OrderMenu(menu, count);
            orderMenuList.add(orderMenu);
        }

        return orderMenuList;
    }

    @Override
    public String toString() {
        return "OrderMenu{" +
                "id=" + id +
//                ", order=" + order +
                ", menu=" + menu +
                ", count=" + count +
                '}';
    }
=======
>>>>>>> 770717b (test)
}
