package com.example.demo.src.cupon;



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
public class CuponService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CuponDao cuponDao;
    private final CuponProvider cuponProvider;
    private final JwtService jwtService;


    @Autowired
    public CuponService(CuponDao cuponDao, CuponProvider cuponProvider, JwtService jwtService) {
        this.cuponDao = cuponDao;
        this.cuponProvider = cuponProvider;
        this.jwtService = jwtService;

    }

}
