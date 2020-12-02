package com.dtr.sp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dtr.sp.bean.TcUser;

public interface TcUserService {
    Page<TcUser> selectPage(TcUser tcUser);
}
