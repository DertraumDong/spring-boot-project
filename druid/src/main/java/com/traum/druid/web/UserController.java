package com.traum.druid.web;

import com.dtr.web.vo.ResponseVO;
import com.traum.druid.model.dto.QueryDto;
import com.traum.druid.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseVO queryList(@RequestBody @Validated QueryDto queryDto){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(userService.queryList(queryDto));
        return responseVO;
    }

    @PostMapping("/queryOtherList")
    public ResponseVO queryOtherList(@RequestBody QueryDto queryDto){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(userService.queryOtherList(queryDto));
        return responseVO;
    }
}
