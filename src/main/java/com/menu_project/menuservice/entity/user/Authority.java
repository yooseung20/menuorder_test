package com.menu_project.menuservice.entity.user;

public enum Authority {
    NOMAL("일반");

    private String value;

    Authority(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }


}