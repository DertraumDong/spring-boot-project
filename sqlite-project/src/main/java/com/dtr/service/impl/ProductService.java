package com.dtr.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dtr.bean.Product;
import com.dtr.repository.ProductRepository;
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
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(List<Product> list){
        for (Product product : list) {
            productRepository.insert(product);
        }
    }

    public List<Product> find(String udiCode){
        return productRepository.selectList(new LambdaQueryWrapper<Product>().eq(Product::getUdiCode,udiCode));
    }

}
