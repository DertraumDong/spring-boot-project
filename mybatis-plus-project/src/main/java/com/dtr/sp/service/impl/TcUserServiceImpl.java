package com.dtr.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dtr.sp.bean.TcUser;
import com.dtr.sp.dao.TcUserMapper;
import com.dtr.sp.service.TcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tcUserService")
public class TcUserServiceImpl implements TcUserService {

    @Autowired
    private TcUserMapper tcUserMapper;

    @Override
    public Page<TcUser> selectPage(TcUser tcUser) {
        Page<TcUser> page = new Page<>(tcUser.getPageNo(),tcUser.getPageSize());
        QueryWrapper<TcUser> queryWrapper = new QueryWrapper<>();
        tcUserMapper.selectPage(page,queryWrapper);
        return page;
    }
}
