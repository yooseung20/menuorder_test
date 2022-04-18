package com.menu_project.menuservice.util;

import com.menu_project.menuservice.dto.CartDto;
import com.menu_project.menuservice.dto.MenuDto;

public class FoodPriceCalc {
    public static int priceCalc(MenuDto.InputMenu cart){
        int foodPrice = cart.getFoodPrice() * cart.getFoodAmount();
        return foodPrice;
    }
}
