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
    public static class MenuInfo {
        private Long menuId;
        private String category;
        private String menuName;
        private int foodPrice;
        private int foodAmount;

    }

    // 카트에 넣기위한 dto
    @NoArgsConstructor
    @Getter
    @Setter
    public static class CartMenu {

        private Menu menu;
        private int foodAmount;
        private int foodTotalPrice;


    public CartMenu(MenuDto.MenuInfo menuInfo) {
        Menu menu  = new Menu(menuInfo.getMenuId(), menuInfo.getCategory(), menuInfo.getMenuName(), menuInfo.getFoodPrice());
        this.menu = menu;
        this.foodAmount = menuInfo.getFoodAmount();
        this.foodTotalPrice = menuInfo.getFoodPrice() * menuInfo.getFoodAmount();
    }
}





}