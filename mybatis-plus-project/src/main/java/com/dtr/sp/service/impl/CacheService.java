package com.dtr.sp.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class CacheService {

    @PostConstruct
    public void init(){
        System.out.println("-----------------");
        for (int i = 0 ; i <= 100 ; i++){
            System.out.println("---------"+ i + "---- : 测试");
        }
    }

}
