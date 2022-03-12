package com.example.demo.src.likelist.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetLikeRes {
    private int likeNum;
    private String userName;
    private String restName;
    private String status;
}

