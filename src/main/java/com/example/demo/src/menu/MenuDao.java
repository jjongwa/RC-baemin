package com.example.demo.src.menu;

import com.example.demo.src.menu.model.*;
import com.example.demo.src.shop.model.GetShopByCateRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MenuDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

// 전체 메뉴 조회
    public List<GetMenuRes> getMenus(){
        String getUsersQuery = "select * from Menu";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetMenuRes(
                        rs.getInt("menuNum"),
                        rs.getInt("restNum"),
                        rs.getString("menuBest"),
                        rs.getString("menuName"),
                        rs.getInt("menuCost"),
                        rs.getString("menuComment"),
                        rs.getString("menuPic"),
                        rs.getString("status")
                )
                );
    }

// 특정 음식점의 메뉴 조회(Menu 테이블에 있는 restNum 참조)
    public List<GetMenuRes> getMenuByRestnum(int restId){
        String getMenuByRestnumQuery = "select * from Menu as M where M.restNum =?";
        int getMenuByRestnumParams = restId;
        System.out.println("dao파일 getShopsByCate 접근");
        System.out.println(restId);
        System.out.println(getMenuByRestnumQuery);
        return this.jdbcTemplate.query(getMenuByRestnumQuery,
                (rs,rowNum) -> new GetMenuRes(
                        rs.getInt("menuNum"),
                        rs.getInt("restNum"),
                        rs.getString("menuBest"),
                        rs.getString("menuName"),
                        rs.getInt("menuCost"),
                        rs.getString("menuComment"),
                        rs.getString("menuPic"),
                        rs.getString("status")),
                getMenuByRestnumParams);
    }




}


/*
    private int menuNum;
    private int restNum;
    private String menuBest;
    private String menuName;
    private int menuCost;
    private String menuComment;
    private String menuPic;
    private String status;
 */