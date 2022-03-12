package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    private String userName;
    private String userId;
    private String userEmail;
    private String userPw;
    private String userPhoto;
    private String userPhone;
    private String userAccount;
}
