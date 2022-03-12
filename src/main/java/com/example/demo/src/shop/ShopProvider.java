package com.example.demo.src.shop;


import com.example.demo.config.BaseException;
import com.example.demo.src.shop.model.*;
import com.example.demo.src.user.model.GetUserRes;
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
public class ShopProvider {

    private final ShopDao shopDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ShopProvider(ShopDao shopDao, JwtService jwtService) {
        this.shopDao = shopDao;
        this.jwtService = jwtService;
    }

// 전체 음식점 조회 API
    public List<GetShopRes> getShops() throws BaseException{
        try{
            List<GetShopRes> getShopRes = shopDao.getShops();
            return getShopRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


// restId로 특정 음식점 조회 API
    public GetShopRes getShop(int restId) throws BaseException {
        try {
            GetShopRes getShopRes = shopDao.getShop(restId);
            return getShopRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


// 카테고리로 음식점 조회 API
    public List<GetShopByCateRes> getShopsByCate(String cate) throws BaseException{
        try{
            List<GetShopByCateRes> getShopsRes = shopDao.getShopsByCate(cate);
            //System.out.println("다오에서 돌려받은 getShopsRes");
            //System.out.println(getShopsRes);
            return getShopsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


// 카테고리로 음식점 조회 API
    public List<GetShopByDelivType> getShopsByDeliv() throws BaseException{
        try{
            System.out.println("provider 진입");
            List<GetShopByDelivType> getShopsRes = shopDao.getShopsByDeliv();
            System.out.println("다오에서 돌려받은 getShopsRes");
            //System.out.println(getShopsRes);
            return getShopsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetShopByDelivType> getShopsByTogo() throws BaseException{
        try{
            System.out.println("provider 진입");
            List<GetShopByDelivType> getShopsRes = shopDao.getShopsByTogo();
            System.out.println("다오에서 돌려받은 getShopsRes");
            //System.out.println(getShopsRes);
            return getShopsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetShopByDelivType> getShopsByBaeminOne() throws BaseException{
        try{
            System.out.println("provider 진입");
            List<GetShopByDelivType> getShopsRes = shopDao.getShopsByBaeminOne();
            System.out.println("다오에서 돌려받은 getShopsRes");
            //System.out.println(getShopsRes);
            return getShopsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
// 검색어로 음식점 조회
    public List<GetShopByCateRes> getShopsByKeyword(String keyword) throws BaseException{
        try{
            List<GetShopByCateRes> getShopsRes = shopDao.getShopsByKeyword(keyword);
            System.out.println("다오에서 돌려받은 getShopsRes");
            System.out.println(getShopsRes);
            return getShopsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }



}
