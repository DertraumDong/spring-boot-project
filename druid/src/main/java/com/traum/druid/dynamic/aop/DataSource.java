package com.traum.druid.dynamic.aop;

import java.lang.annotation.*;

/**
 * 名称：
 * 阐述：
 *
 * @author Administrator
 * @date 2022/7/11 17:24
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String value();
}
