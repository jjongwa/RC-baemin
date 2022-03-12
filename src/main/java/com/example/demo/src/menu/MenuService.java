package com.example.demo.src.menu;



import com.example.demo.config.BaseException;
import com.example.demo.src.menu.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class MenuService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MenuDao menuDao;
    private final MenuProvider menuProvider;
    private final JwtService jwtService;


    @Autowired
    public MenuService(MenuDao menuDao, MenuProvider menuProvider, JwtService jwtService) {
        this.menuDao = menuDao;
        this.menuProvider = menuProvider;
        this.jwtService = jwtService;

    }

}
