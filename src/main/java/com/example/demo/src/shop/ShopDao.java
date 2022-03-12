package com.example.demo.src.shop;

import com.example.demo.src.shop.model.*;
import com.example.demo.src.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ShopDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetShopRes> getShops(){
        String getUsersQuery = "select * from Restaurant";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetShopRes(
                        rs.getInt("restNum"),
                        rs.getString("restName"),
                        rs.getString("restPhone"),
                        rs.getString("restAddr"),
                        rs.getString("restcomment"),
                        rs.getString("minOrder"),
                        rs.getString("restIcon"),
                        rs.getString("status"))
                );
    }


    public GetShopRes getShop(int restNum){
        System.out.println("getShop 들어옴");
        String getShopQuery = "select * from Restaurant where restNum = ?";
        int getShopParams = restNum;
        return  this.jdbcTemplate.queryForObject(getShopQuery,
                (rs,rowNum) -> new GetShopRes(
                        rs.getInt("restNum"),
                        rs.getString("restName"),
                        rs.getString("restPhone"),
                        rs.getString("restAddr"),
                        rs.getString("restcomment"),
                        rs.getString("minOrder"),
                        rs.getString("restIcon"),
                        rs.getString("status")),
                getShopParams);
    }


    public List<GetShopByCateRes> getShopsByCate(String cate){
        String getShopsByCateQuery = "select * from Restaurant as R join Class as C on R.restNum = C.restNum where C.className =?";
        String getShopsByCateParams = cate;
        System.out.println("dao파일 getShopsByCate 접근");
        System.out.println(cate);
        System.out.println(getShopsByCateQuery);
        return this.jdbcTemplate.query(getShopsByCateQuery,
                (rs,rowNum) -> new GetShopByCateRes(
                        rs.getInt("restNum"),
                        rs.getString("restName"),
                        rs.getString("minOrder"),
                        rs.getString("restIcon"),
                        rs.getString("status")),
                getShopsByCateParams);
    }


// 배달 검색 쿼리
    public List<GetShopByDelivType> getShopsByDeliv(){
        //System.out.println("Dao 진입");
        String getShopsByCateQuery = "select * from Restaurant as R where R.baeminOne = 'N'";

        return this.jdbcTemplate.query(getShopsByCateQuery,
                (rs,rowNum) -> new GetShopByDelivType(
                        rs.getInt("R.restNum"),
                        rs.getString("R.restName"),
                        rs.getString("R.minOrder"),
                        rs.getString("R.restIcon"),
                        rs.getString("R.restTogo"),
                        rs.getString("R.baeminOne"),
                        rs.getString("R.status"))
                );
    }
// 포장가능지점 검색 쿼리
    public List<GetShopByDelivType> getShopsByTogo(){
        System.out.println("Dao 진입");
        String getShopsByCateQuery = "select * from Restaurant as R where R.baeminOne = 'N'and R.restTogo = 'Y'";

        return this.jdbcTemplate.query(getShopsByCateQuery,
                (rs,rowNum) -> new GetShopByDelivType(
                        rs.getInt("R.restNum"),
                        rs.getString("R.restName"),
                        rs.getString("R.minOrder"),
                        rs.getString("R.restIcon"),
                        rs.getString("R.restTogo"),
                        rs.getString("R.baeminOne"),
                        rs.getString("R.status"))
        );
    }
//배민원 검색 쿼리
    public List<GetShopByDelivType> getShopsByBaeminOne(){
        System.out.println("Dao 진입");
        String getShopsByCateQuery = "select * from Restaurant as R where R.baeminOne = 'Y'";

        return this.jdbcTemplate.query(getShopsByCateQuery,
                (rs,rowNum) -> new GetShopByDelivType(
                        rs.getInt("R.restNum"),
                        rs.getString("R.restName"),
                        rs.getString("R.minOrder"),
                        rs.getString("R.restIcon"),
                        rs.getString("R.restTogo"),
                        rs.getString("R.baeminOne"),
                        rs.getString("R.status"))
        );
    }

// 키워드 검색 쿼리
    public List<GetShopByCateRes> getShopsByKeyword(String keyword){
        System.out.println("키워드 검색 Dao 진입");
        String getShopsByCateQuery = "select * from Restaurant as R where R.restName like concat('%',?,'%')  ";
        System.out.println("쿼리 받아오기 성공");
        System.out.println(getShopsByCateQuery);
        String getShopsByCateParams = keyword;
        return this.jdbcTemplate.query(getShopsByCateQuery,
                (rs,rowNum) -> new GetShopByCateRes(
                        rs.getInt("restNum"),
                        rs.getString("restName"),
                        rs.getString("minOrder"),
                        rs.getString("restIcon"),
                        rs.getString("status")),
                getShopsByCateParams);
    }


// 영업상태에 따른 음식점 조회 쿼리


//메뉴 이름에 따른 음식점 조회 쿼리(검색)





}


/*
private int restNum;
    private String restName;
    private String restPhone;
    private String restAddr;
    private String restcomment;
    private String minOrder;
    private String restIcon;
    private String status;
 */