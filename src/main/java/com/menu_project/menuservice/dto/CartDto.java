package com.menu_project.menuservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


public class CartDto {

    // 장바구니 추가시 필요한 dto
    @Getter
    @Setter
    public static class Item{
        private Long menuId;
        private String menuName;
        private int foodAmount;
        private int foodPrice;
        private int foodTotalPrice;

    }
    // session에 저장할 dto
    @Getter
    @Setter
    public static class CartList{
        private int totalPrice;
        private List<CartDto.Item> cartList;

        public CartList(int totalPrice, List<Item> cartList) {
            this.totalPrice = totalPrice;
            this.cartList = cartList;
        }
    }
}
