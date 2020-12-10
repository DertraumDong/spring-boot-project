package com.dtr.util;

import java.util.UUID;

/**
 * uuid生成器
 * @since 2020-12-10
 * @author Dertraum
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
