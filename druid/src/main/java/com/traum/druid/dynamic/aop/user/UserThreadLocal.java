package com.traum.druid.dynamic.aop.user;

import com.traum.druid.model.UserInfo;

/**
 * 名称：
 * 阐述：
 *
 * @author Administrator
 * @date 2022/7/11 18:00
 */
public class UserThreadLocal {
    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    /**
     *  设置
     * @param userInfo
     */
    public static void setUserInfo(UserInfo userInfo){
        THREAD_LOCAL.set(userInfo);
    }

    /**
     * 取得当前数据源
     * @return
     */
    public static UserInfo getUserInfo(){
        return THREAD_LOCAL.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clear(){
        THREAD_LOCAL.remove();
    }
}
