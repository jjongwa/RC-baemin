package com.example.demo.src.review;

import com.example.demo.src.review.model.*;
import com.example.demo.src.shop.model.GetShopByCateRes;
import com.example.demo.src.shop.model.GetShopByDelivType;
import com.example.demo.src.user.model.PostUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReviewDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
// 전체 리뷰 조회
    public List<GetReviewRes> getReviews(){
        String getUsersQuery = "select * from Review";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetReviewRes(
                        rs.getInt("revNum"),
                        rs.getInt("userNum"),
                        rs.getInt("restNum"),
                        rs.getInt("orderNum"),
                        rs.getInt("star"),
                        rs.getString("comment")
                        //rs.getString("recPic"))
                )
                );
    }

// 특정 고객의 리뷰 조회
    public List<GetReviewDetail> getReviewByuserNum(int userNum){
        String getReviewQuery = "select * from Review as Rev\n" +
                "    join User U on Rev.userNum = U.userNum\n" +
                "    join Restaurant R on Rev.restNum = R.restNum\n" +
                "where Rev.userNum = ?";
        int getReviewParams = userNum;
        System.out.println("dao파일 getShopsByCate 접근");
        System.out.println(userNum);
        System.out.println(getReviewQuery);
        return this.jdbcTemplate.query(getReviewQuery,
                (rs,rowNum) -> new GetReviewDetail(
                        rs.getInt("revNum"),
                        rs.getString("userName"),
                        rs.getString("restName"),
                        rs.getInt("star"),
                        rs.getString("revPic"),
                        rs.getString("comment")),
                getReviewParams);
    }


// 특정 음식점의 리뷰 조회
    public List<GetReviewDetail> getReviewByrestNum(int restNum){
        String getReviewQuery = "select * from Review as Rev\n" +
                "    join User U on Rev.userNum = U.userNum\n" +
                "    join Restaurant R on Rev.restNum = R.restNum\n" +
                "where Rev.restNum = ?";
        int getReviewParams = restNum;
        System.out.println("dao파일 getShopsByCate 접근");
        System.out.println(restNum);
        System.out.println(getReviewQuery);
        return this.jdbcTemplate.query(getReviewQuery,
                (rs,rowNum) -> new GetReviewDetail(
                        rs.getInt("revNum"),
                        rs.getString("userName"),
                        rs.getString("restName"),
                        rs.getInt("star"),
                        rs.getString("revPic"),
                        rs.getString("comment")),
                getReviewParams);
    }


// 리뷰 생성
public int createReview(PostReviewReq postReviewReq){
    System.out.println("Dao");
    String createUserQuery = "insert into Review (userNum, restNum, " +
            "orderNum, star, comment, revPic ) " +
            "VALUES (?,?,?,?,?,?)";
    Object[] createRevParams = new Object[]{
            postReviewReq.getUserNum(),
            postReviewReq.getRestNum(),
            postReviewReq.getOrderNum(),
            postReviewReq.getStar(),
            postReviewReq.getComment(),
            postReviewReq.getRevPic()
    };
    this.jdbcTemplate.update(createUserQuery, createRevParams);

    String lastInserIdQuery = "select last_insert_id()";
    return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
}





















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

/*
    private int revNum;
    private String userName;
    private String restName;
    private int star;
    private String revPic;
    private String comment;
 */