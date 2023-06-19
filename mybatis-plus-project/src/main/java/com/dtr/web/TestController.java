package com.dtr.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dtr.sp.bean.TcUser;
import com.dtr.sp.service.TcUserService;
import com.dtr.util.UUIDUtil;
import com.dtr.web.vo.ResponseVO;
import com.dtr.web.dto.TcUserQuery;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Api(tags = "测试MybatisPlus")
@ApiSupport(order = 100)
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TcUserService tcUserService;

    @PostMapping("/selectPage")
    @ApiOperation("分页")
    @ApiOperationSupport(order = 1)
    public ResponseVO selectPage(@RequestBody TcUserQuery tcUserQuery){
        ResponseVO responseVO = new ResponseVO();
        IPage<TcUser> page = tcUserService.selectPage(tcUserQuery);
        responseVO.setData(page);
        return responseVO;
    }

    @PostMapping("/insert")
    @ApiOperation("新增")
    @ApiOperationSupport(order = 2)
    public ResponseVO insert(@RequestBody TcUser tcUser){
        ResponseVO responseVO = new ResponseVO();
        int result = tcUserService.insert(tcUser);
        responseVO.setData(result);
        return responseVO;
    }

    @PostMapping("/update")
    @ApiOperation("修改")
    @ApiOperationSupport(order = 3)
    public ResponseVO update(@RequestBody TcUser tcUser){
        ResponseVO responseVO = new ResponseVO();
        int result = tcUserService.update(tcUser);
        responseVO.setData(result);
        return responseVO;
    }

    @PostMapping("/delete")
    @ApiOperation("删除")
    @ApiOperationSupport(order = 4)
    public ResponseVO deleteById(@RequestBody TcUser tcUser){
        ResponseVO responseVO = new ResponseVO();
        int result = tcUserService.deleteById(tcUser);
        responseVO.setData(result);
        return responseVO;
    }

    @GetMapping("/testUUID")
    @ApiOperation("测试uuid")
    @ApiOperationSupport(order = 5)
    public ResponseVO testUUID(){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(UUIDUtil.getUUID());
        return responseVO;
    }
}
