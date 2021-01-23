package com.dtr.core.web;

import com.dtr.core.common.RedisConstant;
import com.dtr.web.dto.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author:DerTraum
 * @date:2021/1/19
 * @since
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    /* 日志记录器 */
    private final static Logger LOGGER = LoggerFactory.getLogger(RedisController .class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @PostMapping("/setValue")
    public ResponseVO setValue(@RequestParam("value") String value, @RequestParam("key") String key){
        redisTemplate.opsForValue().set(RedisConstant.KEY+key,value);
        return new ResponseVO();
    }

    @PostMapping("/getValue")
    public ResponseVO getValue(@RequestParam("key") String key){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(redisTemplate.opsForValue().get(RedisConstant.KEY+key));
        return responseVO;
    }

    @PostMapping("/setHash")
    public ResponseVO setHash(@RequestParam("value") String value, @RequestParam("key") String key){
        redisTemplate.opsForHash().put(RedisConstant.KEY+key,"test",value);
        return new ResponseVO();
    }

    @PostMapping("/getHash")
    public ResponseVO getHash(@RequestParam("key") String key){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(redisTemplate.opsForHash().get(RedisConstant.KEY+key,"test"));
        return responseVO;
    }

    @PostMapping("/getAllKey")
    public ResponseVO getAllKey(){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(redisTemplate.keys("*"));
        return responseVO;
    }

    @PostMapping("/delKey")
    public ResponseVO delKey(@RequestParam("key") String key){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(redisTemplate.delete(RedisConstant.KEY+key));
        return responseVO;
    }
}
