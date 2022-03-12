package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from User";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("userNum"),
                        rs.getString("userName"),
                        rs.getString("userId"),
                        rs.getString("userEmail"),
                        rs.getString("userPw"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String userEmail){
        String getUsersByEmailQuery = "select * from User where userEmail =?";
        String getUsersByEmailParams = userEmail;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userNum"),
                        rs.getString("userName"),
                        rs.getString("userId"),
                        rs.getString("userEmail"),
                        rs.getString("userPw")),
                getUsersByEmailParams);
    }
/*
    // 키워드로 유저 검색
    public List<GetUserRes> getUsersByKeyword(String userName){
        String getUsersByNameQuery = "select * from User where userName like %=?%";
        String getUsersByNameParams = userName;
        return this.jdbcTemplate.query(getUsersByNameQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userNum"),
                        rs.getString("userName"),
                        rs.getString("userId"),
                        rs.getString("userEmail"),
                        rs.getString("userPw")),
                getUsersByNameParams);
    }
*/



    public GetUserRes getUser(int userNum){
        String getUserQuery = "select * from User where userNum = ?";
        int getUserParams = userNum;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userNum"),
                        rs.getString("userName"),
                        rs.getString("userId"),
                        rs.getString("userEmail"),
                        rs.getString("userPw")),
                getUserParams);
    }
    
// photo, phone, account,
    public int createUser(PostUserReq postUserReq){
        System.out.println("다오");
        String createUserQuery = "insert into User (userName, userId, userPw, " +
                "userEmail, userPhoto, userPhone, userAccount ) " +
                "VALUES (?,?,?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getUserName(), postUserReq.getUserId(), postUserReq.getUserPw(), postUserReq.getUserEmail(), postUserReq.getUserPhoto(), postUserReq.getUserPhone(), postUserReq.getUserAccount()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int checkEmail(String userEmail){
        String checkEmailQuery = "select exists(select userEmail from User where userEmail = ?)";
        String checkEmailParams = userEmail;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update User set userName = ? where userNum = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserNum()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select userNum, userPw,userEmail,userName,userId from User where userId = ?";
        String getPwdParams = postLoginReq.getUserId();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("userNum"),
                        rs.getString("userId"),
                        rs.getString("userPw"),
                        rs.getString("userEmail"),
                        rs.getString("userName")
                        //rs.getString("userPhoto"),
                        //rs.getString("userPhone"),
                        //rs.getString("userAccount"),
                        //rs.getString("userClass"),
                        //rs.getInt("userPoint"),
                        //rs.getString("status"),
                        //rs.getTimestamp("createdAt"),
                        //rs.getTimestamp("updatedAt")
                ),
                getPwdParams
                );

    }


}
/*
private int userNum;
    private String userid;
    private String userPw;
    private String userEmail;
    private String userName;
    private String userPhoto;
    private String userPhone;
    private String userAccount;
    private String userClass;
    private int userPoint;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
 */