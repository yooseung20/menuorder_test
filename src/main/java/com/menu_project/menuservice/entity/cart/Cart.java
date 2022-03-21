package com.menu_project.menuservice.entity.cart;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Cart {
    private int cart_no; //장바구니No
    private int cart_mem_no; //회원정보
    private int cart_item_no; //상품정보

    private Date cart_cklimit; //쿠키제한시간(삭제용)
    private String cart_ckid; //쿠키value값

    private String cart_option_content; //옵션내용
    private int cart_option_no; //옵션

}