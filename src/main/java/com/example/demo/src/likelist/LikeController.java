package com.example.demo.src.likelist;

import com.example.demo.src.cupon.model.GetCuponRes;
import com.example.demo.src.menu.model.GetMenuRes;
import com.example.demo.src.review.model.GetReviewDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.likelist.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/like")
public class LikeController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final LikeProvider likeProvider;
    @Autowired
    private final LikeService likeService;
    @Autowired
    private final JwtService jwtService;

    public LikeController(LikeProvider likeProvider, LikeService likeService, JwtService jwtService){
        this.likeProvider = likeProvider;
        this.likeService = likeService;
        this.jwtService = jwtService;
    }

    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetLikeRes>> getLikes(@RequestParam(required = false) String userNum) {
        try{
            // Get Users
            List<GetLikeRes> getLikesRes = likeProvider.getLikes();
            return new BaseResponse<>(getLikesRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    @ResponseBody
    @GetMapping("/{userNum}") // (GET) 127.0.0.1:9000/app/shops/:shopIdx
    public BaseResponse<List<GetLikeRes>> getLikesByuserNum(@PathVariable("userNum") int uesrNum) {
        try{
            List<GetLikeRes> getLikeRes = likeProvider.getLikesByuserNum(uesrNum);
            if(getLikeRes.size() == 0){    // 존재하지 않는 카테고리 검색
                //System.out.println("아무것도 없음");
                return new BaseResponse<>(RESPONSE_EMPTY);
            }
            else {
                return new BaseResponse<>(getLikeRes);
            }
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }









}
