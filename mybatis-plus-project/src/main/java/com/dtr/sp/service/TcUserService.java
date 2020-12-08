package com.dtr.sp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dtr.sp.bean.TcUser;
import com.dtr.web.dto.TcUserQuery;

public interface TcUserService {
    IPage<TcUser> selectPage(TcUserQuery tcUserQuery);
    int insert(TcUser tcUser);
    int update(TcUser tcUser);
    int deleteById(TcUser tcUser);
}
