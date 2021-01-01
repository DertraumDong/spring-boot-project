package com.dtr.web;

import com.dtr.web.dto.ResponesVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @description:
 * @author:DerTraum
 * @date:2020/12/31
 * @since
 */
@RestController
public class TestController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestController .class);

    @PostConstruct
    public void setUp(){
        LOGGER.info("启动打印");
    }

    @PostMapping("/test")
    public ResponesVO getTest(){
        ResponesVO responesVO = new ResponesVO();
        LOGGER.info("test");
        return responesVO;
    }
}
