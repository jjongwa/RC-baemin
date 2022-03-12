package com.example.demo.src.cupon;

import com.example.demo.src.menu.model.GetMenuRes;
import com.example.demo.src.review.model.GetReviewDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.cupon.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/cupon")
public class CuponController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CuponProvider cuponProvider;
    @Autowired
    private final CuponService cuponService;
    @Autowired
    private final JwtService jwtService;

    public CuponController(CuponProvider cuponProvider, CuponService cuponService, JwtService jwtService){
        this.cuponProvider = cuponProvider;
        this.cuponService = cuponService;
        this.jwtService = jwtService;
    }

    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetCuponRes>> getCupons(@RequestParam(required = false) String restNum) {
        try{
            // Get Users
            List<GetCuponRes> getCuponsRes = cuponProvider.getCupons();
            return new BaseResponse<>(getCuponsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }



    //  특정 음식점의 메뉴 조회
    @ResponseBody
    @GetMapping("/{userNum}") // (GET) 127.0.0.1:9000/app/shops/:shopIdx
    public BaseResponse<List<GetCuponRes>> getCuponByuserNum(@PathVariable("userNum") int uesrNum) {
        try{
            List<GetCuponRes> getCuponRes = cuponProvider.getCuponByuserNum(uesrNum);
            if(getCuponRes.size() == 0){    // 존재하지 않는 카테고리 검색
                //System.out.println("아무것도 없음");
                return new BaseResponse<>(RESPONSE_EMPTY);
            }
            else {
                return new BaseResponse<>(getCuponRes);
            }
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }















}
