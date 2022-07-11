package com.traum.druid.mapper;

import com.traum.druid.model.UserInfo;

import java.util.List;

/**
 * 名称：
 * 阐述：
 *
 * @author Administrator
 * @date 2022/7/11 15:21
 */
public interface UserMapper {

    List<UserInfo> queryList();
}
