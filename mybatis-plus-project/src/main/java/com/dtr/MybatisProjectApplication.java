package com.dtr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.dtr.sp.dao"})
public class MybatisProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisProjectApplication.class, args);
    }

}
