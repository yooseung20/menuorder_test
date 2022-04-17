package com.menu_project.menuservice.util;

import com.menu_project.menuservice.dto.CartDto;

public class FoodPriceCalc {
    public static int priceCalc(CartDto.Item cart){
        int foodPrice = cart.getFoodPrice() * cart.getFoodAmount();
        return foodPrice;
    }
}
