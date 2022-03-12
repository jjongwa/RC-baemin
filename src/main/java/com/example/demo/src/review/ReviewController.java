package com.example.demo.src.review;

import com.example.demo.src.shop.model.GetShopByCateRes;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.review.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/review")
public class ReviewController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ReviewProvider reviewProvider;
    @Autowired
    private final ReviewService reviewService;
    @Autowired
    private final JwtService jwtService;

    public ReviewController(ReviewProvider reviewProvider, ReviewService reviewService, JwtService jwtService){
        this.reviewProvider = reviewProvider;
        this.reviewService = reviewService;
        this.jwtService = jwtService;
    }

    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/review
    public BaseResponse<List<GetReviewRes>> getReviews(@RequestParam(required = false) String restNum) {
        try{
            // Get Users
            List<GetReviewRes> getReviewsRes = reviewProvider.getReviews();
            return new BaseResponse<>(getReviewsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 특정 고객의 리뷰 조회 API
     * [GET] /shops/review/user? userNum=
     * @return BaseResponse<List<GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/user") // (GET) 127.0.0.1:9000/app/shops/cate?Cate=
    public BaseResponse<List<GetReviewDetail>> getReviewByuserNum(@RequestParam(required = false) int userNum) {
        try{
            // Get Shops
            //System.out.println(Cate);
            List<GetReviewDetail> getReview = reviewProvider.getReviewByuserNum(userNum);
            if(getReview.size() == 0){    // 존재하지 않는 카테고리 검색
                //System.out.println("아무것도 없음");
                return new BaseResponse<>(RESPONSE_EMPTY);
            }
            else {
                return new BaseResponse<>(getReview);
            }
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 특정 음식정의 리뷰 조회 API
     * [GET] /shops/review/rest? restNum=
     * @return BaseResponse<List<GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/rest") // (GET) 127.0.0.1:9000/app/shops/cate?Cate=
    public BaseResponse<List<GetReviewDetail>> getReviewByrestNum(@RequestParam(required = false) int restNum) {
        try{
            // Get Shops
            //System.out.println(Cate);
            List<GetReviewDetail> getReview = reviewProvider.getReviewByrestNum(restNum);
            if(getReview.size() == 0){    // 존재하지 않는 카테고리 검색
                //System.out.println("아무것도 없음");
                return new BaseResponse<>(RESPONSE_EMPTY);
            }
            else {
                return new BaseResponse<>(getReview);
            }
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }




    /**
     * 리뷰 생성 API
     * [POST] /review
     * @return BaseResponse<PostUserRes>
    */
    // Body
     @ResponseBody
     @PostMapping("")
     public BaseResponse<PostReviewRes> createReview(@RequestBody PostReviewReq postReviewReq) {
     try{
         System.out.println("컨트롤러");
         PostReviewRes postReview = reviewService.createReview(postReviewReq);
     return new BaseResponse<>(postReview);
     } catch(BaseException exception){
     return new BaseResponse<>((exception.getStatus()));
     }
     }


    /**
     * 회원가입 API
     * [POST] /users
     * @return BaseResponse<PostUserRes>

    // Body
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        try{
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    */





}
