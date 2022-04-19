package com.menu_project.menuservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class CartDto {

    // session에 저장할 dto
    @Getter
    @Setter
    public static class CartList{
        private int totalPrice;

        private List<MenuDto.CartMenu> cartList;

        public CartList(int totalPrice, List<MenuDto.CartMenu> cartList) {
            this.totalPrice = totalPrice;
            this.cartList = cartList;
        }
    }


}
