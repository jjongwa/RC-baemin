package com.example.demo.src.cate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.cate.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/cate")
public class CateController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CateProvider cateProvider;
    @Autowired
    private final CateService cateService;
    @Autowired
    private final JwtService jwtService;

    public CateController(CateProvider cateProvider, CateService cateService, JwtService jwtService){
        this.cateProvider = cateProvider;
        this.cateService = cateService;
        this.jwtService = jwtService;
    }

    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetCateRes>> getCates(@RequestParam(required = false) String restNum) {
        try{
            // Get Users
            List<GetCateRes> getCatesRes = cateProvider.getCates();
            return new BaseResponse<>(getCatesRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
