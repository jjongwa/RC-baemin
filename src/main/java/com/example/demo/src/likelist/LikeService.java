package com.example.demo.src.likelist;



import com.example.demo.config.BaseException;
import com.example.demo.src.cupon.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class LikeService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final LikeDao likeDao;
    private final LikeProvider likeProvider;
    private final JwtService jwtService;


    @Autowired
    public LikeService(LikeDao likeDao, LikeProvider likeProvider, JwtService jwtService) {
        this.likeDao = likeDao;
        this.likeProvider = likeProvider;
        this.jwtService = jwtService;

    }

}
