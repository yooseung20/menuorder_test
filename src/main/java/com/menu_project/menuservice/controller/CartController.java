package com.menu_project.menuservice.controller;

import com.menu_project.menuservice.dto.CartDto;

import com.menu_project.menuservice.dto.MenuDto;
import com.menu_project.menuservice.util.FoodPriceCalc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/cart")
public class CartController {
    @GetMapping("/list")
    public CartDto.CartList cartList(HttpSession session) {
        CartDto.CartList cartList = (CartDto.CartList) session.getAttribute("cartList");
        if (cartList != null) {
            return cartList;
        }
        return null;
    }


    @PostMapping("/add")
    public CartDto.CartList addCart(@RequestBody MenuDto.InputMenu menuInfo, HttpSession session) {

        CartDto.CartList cartList = (CartDto.CartList) session.getAttribute("cartList");
        int totalPrice = FoodPriceCalc.priceCalc(menuInfo);

        System.out.println("카트 가격 계산 = " + totalPrice);

        // 기존 카트에 아무값도 없으면, 카트 생성
        if (cartList == null) {
            List<MenuDto.CartMenu> newCart = new ArrayList<>();
            menuInfo.setFoodTotalPrice(totalPrice);
            newCart.add(new MenuDto.CartMenu(menuInfo));
            cartList = new CartDto.CartList(totalPrice, newCart);
        } else {
            // 카트에 기존 내역이 있는경우 prevCart, prevCartTotal(가격)
            List<MenuDto.CartMenu> prevCart = cartList.getCartList();
            int prevCartTotal = cartList.getTotalPrice();
            // cart에 총액을 갱신해준다.
            cartList.setTotalPrice(prevCartTotal + totalPrice);

            // 기존내역이랑 중복되는 메뉴가 들어오는 경우 -> cartList 속 index 값을 찾아서 , amount , price를 고쳐준다.
            if (prevCart.contains(menuInfo)) {
                int cartIndex = prevCart.indexOf(menuInfo);
                int amount = menuInfo.getFoodAmount();

                MenuDto.CartMenu newCart = prevCart.get(cartIndex);
                int newAmount = newCart.getFoodAmount() + amount;
                int newTotal = newCart.getFoodTotalPrice() + totalPrice;

                newCart.setFoodAmount(newAmount);
                newCart.setFoodTotalPrice(newTotal);
                prevCart.set(cartIndex, newCart);
            } else {
                // 기존내역이랑 중복되는 메뉴가 아닌경우 ->  카트에 추가, 총액 계산
                menuInfo.setFoodTotalPrice(totalPrice);
                prevCart.add(new MenuDto.CartMenu(menuInfo));
            }
            cartList.setCartList(prevCart); // cartList안 cart에 추가된 내용을 다시 세팅한다.
        }

        // 세션에 새로운 값 저장
        session.setAttribute("cartList", cartList);

        System.out.println("cartList = " + cartList);

        return cartList;
    }
    // 장바구니 모두 삭제
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteOne(HttpSession session){
        session.removeAttribute("cartList");
        return ResponseEntity.status(HttpStatus.OK).body("장바구니가 모두 비워졌습니다.");
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<String> deleteAll(HttpSession session, @PathVariable int index){
        CartDto.CartList cartList = (CartDto.CartList) session.getAttribute("cartList");
        System.out.println("기존 카트내역을 불러왔습니다.");

        if(cartList == null){
            return ResponseEntity.status(HttpStatus.OK).body("기존 장바구니 내역이 없습니다.");
        }

        // 수정 전 최종 금액
        int totalPrice = cartList.getTotalPrice();
        System.out.println("수정전 금액을 불러왔습니다. : " + totalPrice);
        List<MenuDto.CartMenu> prevCart = cartList.getCartList();
        System.out.println("수정전 카트에 담긴 메뉴를 불러왔습니다. : " + prevCart);

        // 없애야 할 금액
        int deletePrice = prevCart.get(index).getFoodTotalPrice();

        // 카트에서 삭제해준다.
        prevCart.remove(index);
        System.out.println(index + "번의 메뉴를 삭제했습니다.");
        // 최종 금액도 변경해준다.
        cartList.setTotalPrice(totalPrice - deletePrice);
        cartList.setCartList(prevCart);

        // 세션에 다시 저장해준다.
        session.setAttribute("cartList", cartList);
        return ResponseEntity.status(HttpStatus.OK).body("한개의 메뉴가 삭제됬습니다.");
    }
}
