package com.example.demo.src.cupon.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCuponRes {
    private int cuponNum;
    private String cuponName;
    private int cuponCost;
    private String cuponLimit;
    private String cuponRest;
    private String status;
}

