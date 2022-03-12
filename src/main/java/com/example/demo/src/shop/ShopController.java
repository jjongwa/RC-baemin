package com.example.demo.src.shop;

import com.example.demo.src.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.shop.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/shops")
public class ShopController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ShopProvider shopProvider;
    @Autowired
    private final ShopService shopService;
    @Autowired
    private final JwtService jwtService;


    public ShopController(ShopProvider shopProvider, ShopService shopService, JwtService jwtService){
        this.shopProvider = shopProvider;
        this.shopService = shopService;
        this.jwtService = jwtService;
    }

    /**
     *
     * [GET] app/shops
     * @return BaseResponse<GetShopRes>
     */
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetShopRes>> getShops(@RequestParam(required = false) String Cate) {
        try{
            if(Cate == null){
                List<GetShopRes> getShopsRes = shopProvider.getShops();
                return new BaseResponse<>(getShopsRes);
            }
            else {
                // 뒤에 구현되어있지 않은 조건문을 붙였을 때
                return new BaseResponse<>(NO_INPUT_REQUIRED);
            }
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 특정 음식점 조회 API
     * [GET] app/shops/:restIdx
     * @return BaseResponse<GetShopRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{shopIdx}") // (GET) 127.0.0.1:9000/app/shops/:shopIdx
    public BaseResponse<GetShopRes> getShop(@PathVariable("shopIdx") int restNum) {
        //System.out.println(restNum);
        try{
            //System.out.println("try 들어옴");
            GetShopRes getShopRes = shopProvider.getShop(restNum);
            return new BaseResponse<>(getShopRes);
        } catch(BaseException exception){
            //System.out.println("에러 체크");
            return new BaseResponse<>((exception.getStatus()));
        }

    }



    /**
     * 카테고리로 음식점 조회 API
     * [GET] /shops/cate? Cate=
     * @return BaseResponse<List<GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/cate") // (GET) 127.0.0.1:9000/app/shops/cate?Cate=
    public BaseResponse<List<GetShopByCateRes>> getShopsByCateRes(@RequestParam(required = false) String Cate) {
        try{
            if(Cate == null){   // 카테고리를 입력하지 않았을 때
                return new BaseResponse<>(EMPTY_INPUT);
            }
            // Get Shops
            //System.out.println("카테 받아옴");
            //System.out.println(Cate);
            List<GetShopByCateRes> getShopsByCateRes = shopProvider.getShopsByCate(Cate);
            if(getShopsByCateRes.size() == 0){    // 존재하지 않는 카테고리 검색
                //System.out.println("아무것도 없음");
                return new BaseResponse<>(RESPONSE_EMPTY);
            }
            else {
                return new BaseResponse<>(getShopsByCateRes);
            }
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 배달 타입에 따른 음식점 조회 API
     * [GET] /shops/cate? Cate=
     * @return BaseResponse<List<GetShopByDelivType>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/deliv") // (GET) 127.0.0.1:9000/app/shops/deliv?Deliv=
    public BaseResponse<List<GetShopByDelivType>> getShopsByDeliver(@RequestParam(required = false) String Deliv) {
        try{
            System.out.println(Deliv);
            if(Deliv == null){   // 배달타입을 입력하지 않았을 때
                return new BaseResponse<>(WRONG_ACCESS);
            }
            else if(Deliv.equals("배달")) {
                System.out.println("배달로 들어옴");
                List<GetShopByDelivType> getShopsByDelivRes = shopProvider.getShopsByDeliv();
                if(getShopsByDelivRes.size() == 0){    // 존재하지 않는 카테고리 검색
                    return new BaseResponse<>(RESPONSE_EMPTY);
                }
                else {
                    return new BaseResponse<>(getShopsByDelivRes);
                }
            }
            else if(Deliv.equals("포장")){
                System.out.println("포장으로 들어옴");
                List<GetShopByDelivType> getShopsByTogo = shopProvider.getShopsByTogo();
                if(getShopsByTogo.size() == 0){    // 존재하지 않는 카테고리 검색
                    return new BaseResponse<>(RESPONSE_EMPTY);
                }
                else {
                    return new BaseResponse<>(getShopsByTogo);
                }
            }
            else if(Deliv.equals("배민원")){
                System.out.println("배민원으로 들어옴");
                List<GetShopByDelivType> getShopsByBaeminOne = shopProvider.getShopsByBaeminOne();
                if(getShopsByBaeminOne.size() == 0){    // 존재하지 않는 카테고리 검색
                    return new BaseResponse<>(RESPONSE_EMPTY);
                }
                else {
                    return new BaseResponse<>(getShopsByBaeminOne);
                }
            }
            else{
                System.out.println("입력값이 같지 않음");
                return new BaseResponse<>(WRONG_ACCESS);
            }

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 검색어로 음식점 조회 API
     * [GET] /shops/keyword? Keyword
     * @return BaseResponse<List<GetShopByCateRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/keyword") // (GET) 127.0.0.1:9000/app/shops/cate?Cate=
    public BaseResponse<List<GetShopByCateRes>> getShopsByKeyword(@RequestParam(required = false) String Keyword) {
        try{
            if(Keyword == null){   // 카테고리를 입력하지 않았을 때
                return new BaseResponse<>(EMPTY_INPUT);
            }
            System.out.println("키워드 받아옴");
            System.out.println(Keyword);
            List<GetShopByCateRes> getShopsByKeyword = shopProvider.getShopsByKeyword(Keyword);
            if(getShopsByKeyword.size() == 0){    // 존재하지 않는 카테고리 검색
                //System.out.println("아무것도 없음");
                return new BaseResponse<>(RESPONSE_EMPTY);
            }
            else {
                return new BaseResponse<>(getShopsByKeyword);
            }
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }



}
