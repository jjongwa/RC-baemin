package com.example.demo.src.cupon;

import com.example.demo.src.cupon.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CuponDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


// 전체 쿠폰 조회
    public List<GetCuponRes> getCupons(){
        String getUsersQuery = "select * from Cupon";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetCuponRes(
                        rs.getInt("cuponNum"),
                        rs.getString("cuponName"),
                        rs.getInt("cuponCost"),
                        rs.getString("cuponLimit"),
                        rs.getString("cuponRest"),
                        rs.getString("status")
                        //rs.getString("recPic"))
                )
                );
    }

// 특정 사용자가 가진 쿠폰 조회
    public List<GetCuponRes> getCuponsByuserNum(int userNum){
        String getCuponsQuery = "select * from Cupon as C join CuponList as CL on CL.cuponNum = C.cuponNum where CL.userNum = ?";
        int getCuponParams = userNum;
        return this.jdbcTemplate.query(getCuponsQuery,
                (rs,rowNum) -> new GetCuponRes(
                        rs.getInt("cuponNum"),
                        rs.getString("cuponName"),
                        rs.getInt("cuponCost"),
                        rs.getString("cuponLimit"),
                        rs.getString("cuponRest"),
                        rs.getString("status")
                        //rs.getString("recPic"))
                ), getCuponParams
        );
    }







}


/*
    private int cuponNum;
    private String cuponName;
    private int cuponCost;
    //private String cuponLimit;
    private String cuponRest;
    private String status;
 */