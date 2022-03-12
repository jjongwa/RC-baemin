package com.example.demo.src.menu.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMenuRes {
    private int menuNum;
    private int restNum;
    private String menuBest;
    private String menuName;
    private int menuCost;
    private String menuComment;
    private String menuPic;
    private String status;
}

