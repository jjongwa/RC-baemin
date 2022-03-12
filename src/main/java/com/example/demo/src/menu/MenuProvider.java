package com.example.demo.src.menu;


import com.example.demo.config.BaseException;
import com.example.demo.src.menu.model.*;
import com.example.demo.src.shop.model.GetShopByCateRes;
import com.example.demo.src.shop.model.GetShopRes;
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
public class MenuProvider {

    private final MenuDao menuDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MenuProvider(MenuDao menuDao, JwtService jwtService) {
        this.menuDao = menuDao;
        this.jwtService = jwtService;
    }

    public List<GetMenuRes> getMenus() throws BaseException{
        try{
            List<GetMenuRes> getMenuRes = menuDao.getMenus();
            return getMenuRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

// 특정 음식점의 메뉴 조회
    public List<GetMenuRes> getMenuByRestnum(int restNum) throws BaseException{
        try{
            List<GetMenuRes> getMenuByRest = menuDao.getMenuByRestnum(restNum);
            //System.out.println("다오에서 돌려받은 getShopsRes");
            //System.out.println(getMenuByRest);
            return getMenuByRest;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }



















}
