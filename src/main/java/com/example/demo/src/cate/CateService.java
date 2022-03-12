package com.example.demo.src.cate;



import com.example.demo.config.BaseException;
import com.example.demo.src.cate.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class CateService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CateDao cateDao;
    private final CateProvider cateProvider;
    private final JwtService jwtService;


    @Autowired
    public CateService(CateDao cateDao, CateProvider cateProvider, JwtService jwtService) {
        this.cateDao = cateDao;
        this.cateProvider = cateProvider;
        this.jwtService = jwtService;

    }

}
