package com.dtr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author:DerTraum
 * @date:2024/3/15
 * @since
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.dtr.repository"})
public class SqliteProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SqliteProjectApplication.class, args);
    }
}
