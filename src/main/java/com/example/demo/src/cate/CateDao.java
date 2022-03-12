package com.example.demo.src.cate;

import com.example.demo.src.cate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CateDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetCateRes> getCates(){
        String getUsersQuery = "select distinct  className from Class";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetCateRes(
                        //rs.getInt("classNum"),
                        //rs.getInt("restNum"),
                        rs.getString("className"))
                        //rs.getString("status"))
                );
    }

}


/*
 private int classNum;
    private int restNum;
    private String className;
    private String status;
 */