package com.dtr.sp.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Slf4j
@Service
public class CacheService {

    @PostConstruct
    public void init(){
        log.info("-----------------");
        //System.out.println("-----------------");
        for (int i = 0 ; i <= 2 ; i++){
            log.info("---------"+ i + "---- : 测试");
            //System.out.println("---------"+ i + "---- : 测试");
        }
    }

}
