package com.dtr.web;


import com.dtr.bean.Product;
import com.dtr.bean.ProductTest;
import com.dtr.service.impl.ProductService;
import com.dtr.service.impl.ProductTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liudong
 * 2024/3/15 14:11
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/product/test")
public class ProductTestWeb {

    @Resource
    private ProductTestService productTestService;

    @GetMapping("/tranProduct")
    public void one(){

    }

    @GetMapping("/find")
    public Iterable<ProductTest> find(){
        return productTestService.find();
    }

    @GetMapping("/insertBig")
    public Integer insertBig(@RequestParam(name = "size") Integer size){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("open");
        log.info("新增大小:{}",size);
        Integer result = productTestService.insertBig(size);
        stopWatch.stop();
        log.info("插入,{}", stopWatch.prettyPrint());
        log.info("插入：{}", stopWatch.getTotalTimeSeconds());
        return result;
    }
}
