package com.dtr;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

/**
 * @description:
 * @author:DerTraum
 * @date:2024/3/15
 * @since
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.dtr.repository"})
public class SqliteProjectApplication{

    public static void main(String[] args) {
        SpringApplication.run(SqliteProjectApplication.class, args);
    }

}
