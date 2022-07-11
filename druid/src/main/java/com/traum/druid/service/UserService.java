package com.traum.druid.service;

import com.traum.druid.dynamic.aop.DataSource;
import com.traum.druid.dynamic.aop.user.UserThreadLocal;
import com.traum.druid.mapper.UserMapper;
import com.traum.druid.model.UserInfo;
import com.traum.druid.model.dto.QueryDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 名称：
 * 阐述：
 *
 * @author Administrator
 * @date 2022/7/11 15:13
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @DataSource("traum")
    public List<UserInfo> queryList(QueryDto queryDto){
        System.out.println("tendId = "+UserThreadLocal.getUserInfo().getTendId());
        return userMapper.queryList();
    }

    @DataSource("traum_2")
    public List<UserInfo> queryOtherList(QueryDto queryDto){
        return userMapper.queryList();
    }
}
