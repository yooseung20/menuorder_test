package com.menu_project.menuservice.dto;

import com.menu_project.menuservice.entity.menu_order.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class MenuDto {
    // 장바구니에 추가시 담을 dto
    @Getter
    @Setter
    @NoArgsConstructor
    public static class InputMenu{
        private Long menuId;
        private String menuName;
        private int foodPrice;

        private int foodAmount;
        private int foodTotalPrice;
        }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class CartMenu{

        private Menu menu;
        private int foodAmount;
        private int foodTotalPrice;

        public CartMenu(InputMenu menuInfo) {
            this.menu = new Menu(menuInfo.menuId, menuInfo.foodPrice);
            this.foodAmount = foodAmount;
            this.foodTotalPrice = foodAmount * menuInfo.foodPrice;
        }
    }

    @Getter
    public static class MenuInfoList{

    }
}