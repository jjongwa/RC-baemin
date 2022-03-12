package com.example.demo.src.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Shop {
    @NotNull
    private int restNum;
    private String restName;
    private String restPhone;
    private String restAddr;
    private String restRealName;
    private String restOwner;
    private String restRegist;
    private String restOwnAddr;
    private String restClass;
    private String restTime;
    private String restOffday;
    private String restcomment;
    private String minOrder;
    private String baeminOne;
    private String restEat;
    private String restTogo;
    private String restIcon;
    private String status;
}


