package com.dtr.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dtr.sp.bean.TcUser;
import com.dtr.sp.dao.TcUserMapper;
import com.dtr.sp.service.TcUserService;
import com.dtr.web.dto.TcUserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("tcUserService")
public class TcUserServiceImpl implements TcUserService {

    @Autowired
    private TcUserMapper tcUserMapper;

    @Override
    public IPage<TcUser> selectPage(TcUserQuery tcUserQuery) {
        IPage<TcUser> page = new Page<>(tcUserQuery.getPageNo(),tcUserQuery.getPageSize());
        QueryWrapper<TcUser> queryWrapper = new QueryWrapper<>();
        tcUserMapper.selectPage(page,queryWrapper);
        return page;
    }

    @Override
    public int insert(TcUser tcUser) {
        tcUser.setId(UUID.randomUUID().toString().replaceAll("-",""));
        return tcUserMapper.insert(tcUser);
    }

    @Override
    public int update(TcUser tcUser) {
        return tcUserMapper.update(tcUser,null);
    }

    @Override
    public int deleteById(TcUser tcUser) {
        return tcUserMapper.deleteById(tcUser.getId());
    }
}
