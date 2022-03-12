package com.example.demo.src.menu;

import com.example.demo.src.shop.model.GetShopByCateRes;
import com.example.demo.src.shop.model.GetShopRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.menu.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/menu")
public class MenuController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final MenuProvider menuProvider;
    @Autowired
    private final MenuService menuService;
    @Autowired
    private final JwtService jwtService;

    public MenuController(MenuProvider menuProvider, MenuService menuService, JwtService jwtService){
        this.menuProvider = menuProvider;
        this.menuService = menuService;
        this.jwtService = jwtService;
    }

    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetMenuRes>> getMenus(@RequestParam(required = false) String restNum) {
        try{
            // Get Users
            List<GetMenuRes> getMenuRes = menuProvider.getMenus();
            return new BaseResponse<>(getMenuRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//  특정 음식점의 메뉴 조회
    @ResponseBody
    @GetMapping("/{restNum}") // (GET) 127.0.0.1:9000/app/shops/:shopIdx
    public BaseResponse<List<GetMenuRes>> getMenuByRestnum(@PathVariable("restNum") int restNum) {
        try{
            List<GetMenuRes> getMenuByRestnum = menuProvider.getMenuByRestnum(restNum);
            if(getMenuByRestnum.size() == 0){    // 존재하지 않는 카테고리 검색
                //System.out.println("아무것도 없음");
                return new BaseResponse<>(RESPONSE_EMPTY);
            }
            else {
                return new BaseResponse<>(getMenuByRestnum);
            }
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

/**
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

    */






}
