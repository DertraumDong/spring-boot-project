package com.dtr.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dtr.sp.bean.TcUser;
import com.dtr.sp.service.TcUserService;
import com.dtr.web.dto.ResponesVO;
import com.dtr.web.dto.TcUserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TcUserService tcUserService;

    @PostMapping("/selectPage")
    public ResponesVO selectPage(@RequestBody TcUserQuery tcUserQuery){
        ResponesVO responesVO = new ResponesVO();
        IPage<TcUser> page = tcUserService.selectPage(tcUserQuery);
        responesVO.setData(page);
        return responesVO;
    }

    @PostMapping("/insert")
    public ResponesVO insert(@RequestBody TcUser tcUser){
        ResponesVO responesVO = new ResponesVO();
        int result = tcUserService.insert(tcUser);
        responesVO.setData(result);
        return responesVO;
    }

    @PostMapping("/update")
    public ResponesVO update(@RequestBody TcUser tcUser){
        ResponesVO responesVO = new ResponesVO();
        int result = tcUserService.update(tcUser);
        responesVO.setData(result);
        return responesVO;
    }

    @PostMapping("/delete")
    public ResponesVO deleteById(@RequestBody TcUser tcUser){
        ResponesVO responesVO = new ResponesVO();
        int result = tcUserService.deleteById(tcUser);
        responesVO.setData(result);
        return responesVO;
    }
}
