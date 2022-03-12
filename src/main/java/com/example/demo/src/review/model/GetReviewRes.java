package com.example.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewRes {
    private int revNum;
    private int userNum;
    private int restNum;
    private int orderNum;
    private int star;
    private String comment;
    //private String revPic;

}

/*
private int revNum;
    private int userNum;
    private int restNum;
    private int orderNum;
    private int star;
    private String comment;
    private String revPic;
 */