package com.example.demo.src.review;


import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.*;
import com.example.demo.src.shop.model.GetShopByCateRes;
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
public class ReviewProvider {

    private final ReviewDao reviewDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ReviewProvider(ReviewDao reviewDao, JwtService jwtService) {
        this.reviewDao = reviewDao;
        this.jwtService = jwtService;
    }

    public List<GetReviewRes> getReviews() throws BaseException{
        try{
            List<GetReviewRes> getReviewRes = reviewDao.getReviews();
            return getReviewRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public List<GetReviewDetail> getReviewByuserNum(int userNum) throws BaseException{
        try{
            List<GetReviewDetail> getReviewRes = reviewDao.getReviewByuserNum(userNum);
            return getReviewRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public List<GetReviewDetail> getReviewByrestNum(int restNum) throws BaseException{
        try{
            List<GetReviewDetail> getReviewRes = reviewDao.getReviewByrestNum(restNum);
            return getReviewRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
