package com.traum.druid.dynamic.config;

import com.traum.druid.dynamic.holder.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 名称：
 * 阐述：
 *
 * @author Administrator
 * @date 2022/7/11 18:01
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
