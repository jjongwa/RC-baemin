package com.example.demo.src.likelist;

import com.example.demo.src.cupon.model.GetCuponRes;
import com.example.demo.src.likelist.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class LikeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


// 전체 쿠폰 조회
    public List<GetLikeRes> getLikes(){
        String getUsersQuery = "select * from LikeRest as L\n" +
                "    join User as U on L.userNum = U.userNum\n" +
                "    join Restaurant R on L.restNum = R.restNum  ";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetLikeRes(
                        rs.getInt("likeNum"),
                        rs.getString("U.userName"),
                        rs.getString("R.restName"),
                        rs.getString("status")
                )
                );
    }

    public List<GetLikeRes> getLikesByuserNum(int userNum){
        String getUsersQuery = "select * from LikeRest as L\n" +
                "    join User as U on L.userNum = U.userNum\n" +
                "    join Restaurant R on L.restNum = R.restNum where U.userNum = ? ";
        int getLikeParams = userNum;
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetLikeRes(
                        rs.getInt("likeNum"),
                        rs.getString("U.userName"),
                        rs.getString("R.restName"),
                        rs.getString("status")
                ), getLikeParams
        );
    }






}

