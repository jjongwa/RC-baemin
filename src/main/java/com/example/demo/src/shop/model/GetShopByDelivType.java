package com.example.demo.src.shop.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetShopByDelivType {
    private int restNum;
    private String restName;
    private String minOrder;
    private String restIcon;
    private String restTogo;
    private String baeminOne;
    private String status;
}