package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class User {
    @NotNull
    private int userNum;
    private String userId;
    private String userPw;
    private String userEmail;
    private String userName;
    //private String userPhoto;
    //private String userPhone;
    //private String userAccount;
    //private String userClass;
    //private int userPoint;
    //private String status;
    //private Timestamp createdAt;
    //private Timestamp updatedAt;
}


