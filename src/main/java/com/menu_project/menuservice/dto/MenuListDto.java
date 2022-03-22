package com.menu_project.menuservice.dto;

import com.menu_project.menuservice.entity.menu.Menu;
import lombok.Getter;

@Getter
public class MenuListDto {
    private Long id;
    private String type;
    private String menu_name;
    private Long price;

    public MenuListDto(Menu entity){
        this.id = entity.getId();
        this.type = entity.getType();
        this.menu_name = entity.getMenu_name();
        this.price = entity.getPrice();
    }
}
