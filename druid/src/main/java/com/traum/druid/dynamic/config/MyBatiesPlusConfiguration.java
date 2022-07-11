package com.traum.druid.dynamic.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.traum.druid.dynamic.DataInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 名称：
 * 阐述：
 *
 * @author Administrator
 * @date 2022/7/11 18:09
 */
@Configuration
@MapperScan("com.traum.druid.*.*.mapper")
public class MyBatiesPlusConfiguration {
    private static Map<String, DataInfo> dataMap = new HashMap();
    static{
        dataMap.put("traum",new DataInfo("jdbc:mysql://127.0.0.1:3306/traum?useUnicode=true&characterEncoding=utf8&useSSL=true","traum","621248","com.mysql.cj.jdbc.Driver"));
        dataMap.put("traum_2",new DataInfo("jdbc:mysql://127.0.0.1:3306/traum_2?useUnicode=true&characterEncoding=utf8&useSSL=true","traum_2","621248","com.mysql.cj.jdbc.Driver"));
    }

    //  标识为第一个数据源
    @Bean("truam")
    DataSource database1() {
        DruidDataSource build = DruidDataSourceBuilder.create().build();
        build.setUrl("jdbc:mysql://127.0.0.1:3306/traum?useUnicode=true&characterEncoding=utf8&useSSL=true");
        build.setUsername("traum");
        build.setPassword("621248");
        build.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return build;
    }

    @Bean("truam_2")
    DataSource database2() {
        DruidDataSource build = DruidDataSourceBuilder.create().build();
        build.setUrl("jdbc:mysql://127.0.0.1:3306/traum_2?useUnicode=true&characterEncoding=utf8&useSSL=true");
        build.setUsername("traum");
        build.setPassword("621248");
        build.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return build;
    }
    /**
     * 动态数据源配置
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource() {
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        Map< Object, Object > targetDataSources = new HashMap<>();
        targetDataSources.put("traum", database1());
        targetDataSources.put("traum_2", database2());
        //添加数据源
        multipleDataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源
        multipleDataSource.setDefaultTargetDataSource(database1());
        return multipleDataSource;
    }
}
