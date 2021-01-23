package com.dtr.list.service;

import com.dtr.core.common.RedisConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @description:
 * @author:DerTraum
 * @date:2021/1/23
 * @since
 */
@Configuration
@EnableScheduling
public class ScheduledTask {

    /* 日志记录器 */
    private final static Logger LOGGER = LoggerFactory.getLogger(ScheduledTask .class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private String[] ids = new String[]{"10001","10002","10003","10005","10007","10008","10009"
            ,"10011","10012","10013","10015","10018"};

    @Scheduled(cron = "0/30 * * * * ?")
    private void refresh(){
        LOGGER.info("ScheduledTask => refresh");
        for (String id : ids) {
            int num = (int) (Math.random()*10);
            Double aDouble = redisTemplate.opsForZSet().score(RedisConstant.KEY,id);
            aDouble = aDouble==null?0:aDouble;
            num = (int) (aDouble + num);
            redisTemplate.opsForZSet().add(RedisConstant.KEY,id,num);
        }
    }

}
