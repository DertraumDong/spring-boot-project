package com.dtr.list.web;

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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 利用redis的特性做排行榜
 * @Author DerTraum
 * @date: 2021/1/23
 * @since 1.0.0
 */
@RestController
@RequestMapping("/list")
public class ListController {
    /* 日志记录器 */
    private final static Logger LOGGER = LoggerFactory.getLogger(ListController .class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @PostMapping("/queryList")
    public ResponseVO queryList(@RequestParam("size") int size){
        ResponseVO responseVO = new ResponseVO();
        if(size <= 0){
            size = 10;
        }
        responseVO.setData(redisTemplate.opsForZSet().reverseRangeWithScores(RedisConstant.KEY,0,size));
        return responseVO;
    }

    @PostMapping("/insertPai")
    public ResponseVO insertPai(@RequestParam("articleId") String articleId,@RequestParam("num") Double num){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(redisTemplate.opsForZSet().add(RedisConstant.KEY,articleId,num));
        return responseVO;
    }

    @PostMapping("/addVote")
    public ResponseVO addVote(@RequestParam("articleId") String articleId){
        ResponseVO responseVO = new ResponseVO();
        int index = (int) (Math.random()*10);
        String userId = RedisConstant.ids[index];
        Set<String> userIds = (Set<String>) redisTemplate.opsForHash().get(RedisConstant.VOTE_KEY,articleId);
        if(userIds==null ){
            userIds = new HashSet<>();
        }
        if(userIds.add(userId)){
            redisTemplate.opsForHash().put(RedisConstant.VOTE_KEY,articleId,userIds);
        }else{
            responseVO.setCode("10005");
            responseVO.setMsg("您已投过票。");
        }
        return responseVO;
    }
    @PostMapping("/getVoteArticleId")
    public ResponseVO getVoteUserId(@RequestParam("articleId") String articleId){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(redisTemplate.opsForHash().get(RedisConstant.VOTE_KEY,articleId));
        return responseVO;
    }


}
