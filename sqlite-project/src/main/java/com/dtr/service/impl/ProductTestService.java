package com.dtr.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dtr.bean.Product;
import com.dtr.bean.ProductTest;
import com.dtr.repository.ProductRepository;
import com.dtr.repository.ProductTestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liudong
 * 2024/3/15 14:17
 * @version 1.0
 */
@Slf4j
@Service
public class ProductTestService {

    @Autowired
    private ProductTestRepository productTestRepository;

    public void saveProduct(List<ProductTest> list){
        for (ProductTest productTest : list) {
            productTestRepository.insert(productTest);
        }
    }

    public List<ProductTest> find(){
        return productTestRepository.selectList(null);
    }


    public Integer insertBig(Integer size){
        int result = 0 ;
        for (int i = 0; i < size; i++) {
            ProductTest build = ProductTest.builder().udiCode("69" + i).yiBaoCode("C03" + i).build();
            result += productTestRepository.insert(build);
        }
        return result;
    }
}
