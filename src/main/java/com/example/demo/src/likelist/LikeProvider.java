package com.example.demo.src.likelist;


import com.example.demo.config.BaseException;
import com.example.demo.src.cupon.model.GetCuponRes;
import com.example.demo.src.likelist.model.*;
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
public class LikeProvider {

    private final LikeDao likeDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public LikeProvider(LikeDao likeDao, JwtService jwtService) {
        this.likeDao = likeDao;
        this.jwtService = jwtService;
    }

    public List<GetLikeRes> getLikes() throws BaseException{
        try{
            List<GetLikeRes> getLikeRes = likeDao.getLikes();
            return getLikeRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetLikeRes> getLikesByuserNum(int userNum) throws BaseException{
        try{
            List<GetLikeRes> getLikeRes = likeDao.getLikesByuserNum(userNum);
            return getLikeRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }



}
