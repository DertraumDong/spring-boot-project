package com.dtr.web;


import com.dtr.bean.Product;
import com.dtr.config.handler.ProductDataSave;
import com.dtr.service.impl.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liudong
 * 2024/3/15 14:11
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductWeb {

    @Resource
    private ProductService productService;

    @GetMapping("/tranProduct")
    public void tran(){
        ProductDataSave.mainMethod();
    }

    @GetMapping("/find")
    public List<Product> find(@RequestParam(name = "udiCode") String udiCode){
        //log.info("find:{}",udiCode);
        return productService.find(udiCode);
    }

}
