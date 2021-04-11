package com.dtr.web;

import com.dtr.bean.CatEmailLog;
import com.dtr.bean.dto.CatEmailLogDto;
import com.dtr.service.CatEmailLogService;
import com.dtr.web.dto.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created on 2021/4/11.
 *
 * @author LiuDong
 */
@RestController
@RequestMapping("/catEmailLog")
public class CatEmailLogController {

    @Autowired
    private CatEmailLogService catEmailLogService;

    @PutMapping("/add")
    public ResponseVO add(@RequestBody CatEmailLogDto catEmailLogDto){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(catEmailLogService.add(catEmailLogDto));
        return responseVO;
    }

    @PostMapping("/findPage")
    public ResponseVO findPage(@RequestBody CatEmailLogDto catEmailLogDto){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(catEmailLogService.findPage(catEmailLogDto));
        return responseVO;
    }

    @PutMapping("/update")
    public ResponseVO update(@RequestBody CatEmailLogDto catEmailLogDto) throws Exception {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(catEmailLogService.update(catEmailLogDto));
        return responseVO;
    }

    @DeleteMapping("/delete")
    public ResponseVO delete(@RequestBody CatEmailLogDto catEmailLogDto) throws Exception {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(catEmailLogService.deleteById(catEmailLogDto.getId()));
        return responseVO;
    }

}
