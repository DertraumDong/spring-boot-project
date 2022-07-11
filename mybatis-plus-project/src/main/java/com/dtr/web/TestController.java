package com.dtr.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dtr.sp.bean.TcUser;
import com.dtr.sp.service.TcUserService;
import com.dtr.util.UUIDUtil;
import com.dtr.web.vo.ResponseVO;
import com.dtr.web.dto.TcUserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TcUserService tcUserService;

    @PostMapping("/selectPage")
    public ResponseVO selectPage(@RequestBody TcUserQuery tcUserQuery){
        ResponseVO responseVO = new ResponseVO();
        IPage<TcUser> page = tcUserService.selectPage(tcUserQuery);
        responseVO.setData(page);
        return responseVO;
    }

    @PostMapping("/insert")
    public ResponseVO insert(@RequestBody TcUser tcUser){
        ResponseVO responseVO = new ResponseVO();
        int result = tcUserService.insert(tcUser);
        responseVO.setData(result);
        return responseVO;
    }

    @PostMapping("/update")
    public ResponseVO update(@RequestBody TcUser tcUser){
        ResponseVO responseVO = new ResponseVO();
        int result = tcUserService.update(tcUser);
        responseVO.setData(result);
        return responseVO;
    }

    @PostMapping("/delete")
    public ResponseVO deleteById(@RequestBody TcUser tcUser){
        ResponseVO responseVO = new ResponseVO();
        int result = tcUserService.deleteById(tcUser);
        responseVO.setData(result);
        return responseVO;
    }

    @GetMapping("/testUUID")
    public ResponseVO testUUID(){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(UUIDUtil.getUUID());
        return responseVO;
    }
}
