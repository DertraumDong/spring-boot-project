package com.traum.druid.dynamic.aop;

import com.traum.druid.dynamic.aop.user.UserThreadLocal;
import com.traum.druid.dynamic.holder.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 名称：
 * 阐述：
 *
 * @author Administrator
 * @date 2022/7/11 18:01
 */
@Component
@Slf4j
@Aspect
@Order(-1)
public class DataSourceAspect {

    @Pointcut("@within(com.traum.druid.dynamic.aop.DataSource) || @annotation(com.traum.druid.dynamic.aop.DataSource)")
    public void pointCut(){

    }

    @Before("pointCut() && @annotation(dataSource)")
    public void doBefore(DataSource dataSource){
        String tendId = UserThreadLocal.getUserInfo().getTendId();
        String dataName = DataSourceEnum.getDataNameByTendId(tendId);
        log.info("选择数据源---"+dataName);
        DataSourceContextHolder.setDataSource(dataName);
    }

    @After("pointCut()")
    public void doAfter(){
        DataSourceContextHolder.clear();
    }
}
