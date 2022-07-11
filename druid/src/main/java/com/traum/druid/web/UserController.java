package com.traum.druid.web;

import com.dtr.web.vo.ResponseVO;
import com.traum.druid.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 名称：
 * 阐述：
 *
 * @author Administrator
 * @date 2022/7/11 15:26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("/queryList")
    public ResponseVO queryList(){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(userService.queryList());
        return responseVO;
    }

    @PostMapping("/queryOtherList")
    public ResponseVO queryOtherList(){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(userService.queryOtherList());
        return responseVO;
    }
}
