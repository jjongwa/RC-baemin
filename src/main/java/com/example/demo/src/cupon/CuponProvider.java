package com.example.demo.src.cupon;


import com.example.demo.config.BaseException;
import com.example.demo.src.cupon.model.*;
import com.example.demo.src.review.model.GetReviewDetail;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class CuponProvider {

    private final CuponDao cuponDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CuponProvider(CuponDao cuponDao, JwtService jwtService) {
        this.cuponDao = cuponDao;
        this.jwtService = jwtService;
    }

    public List<GetCuponRes> getCupons() throws BaseException{
        try{
            List<GetCuponRes> getCuponRes = cuponDao.getCupons();
            return getCuponRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public List<GetCuponRes> getCuponByuserNum(int userNum) throws BaseException{
        try{
            List<GetCuponRes> getCuponRes = cuponDao.getCuponsByuserNum(userNum);
            return getCuponRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }




}
