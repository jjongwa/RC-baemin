package com.example.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewDetail {
    private int revNum;
    private String userName;
    private String restName;
    private int star;
    private String revPic;
    private String comment;
}