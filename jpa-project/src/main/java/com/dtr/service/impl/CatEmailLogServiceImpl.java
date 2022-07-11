package com.dtr.service.impl;

import com.dtr.bean.CatEmailLog;
import com.dtr.bean.dto.CatEmailLogDto;
import com.dtr.repository.CatEmailLogRepository;
import com.dtr.service.CatEmailLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created on 2021/4/11.
 *
 * @author LiuDong
 */
@Service("catEmailLogService")
public class CatEmailLogServiceImpl implements CatEmailLogService {
    @Autowired
    private CatEmailLogRepository catEmailLogRepository;
    @Transactional
    @Override
    public CatEmailLog add(CatEmailLogDto catEmailLogDto) {
        CatEmailLog catEmailLog = new CatEmailLog();
        BeanUtils.copyProperties(catEmailLogDto,catEmailLog);
        catEmailLog.setCreateUserId("admin");
        catEmailLog.setCreateTime(new Date());
        catEmailLog.setUpdateUserId("admin");
        catEmailLog.setUpdateTime(new Date());
        catEmailLog.setVersion(1);
        return catEmailLogRepository.save(catEmailLog);
    }

    @Override
    public Page<CatEmailLog> findPage(CatEmailLogDto catEmailLogDto) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        Pageable pageable = PageRequest.of(catEmailLogDto.getPageNo()-1, catEmailLogDto.getPageSize(), sort);
        Page<CatEmailLog> page = catEmailLogRepository.findAll(pageable);
        return page;
    }

    @Override
    public CatEmailLog update(CatEmailLogDto catEmailLogDto) throws Exception {
        if(null == catEmailLogDto.getId()){
            throw new Exception("没有主键ID");
        }
        CatEmailLog result = catEmailLogRepository.getById(catEmailLogDto.getId());
        if(null == result){
            throw new Exception("主键ID错误");
        }
        CatEmailLog catEmailLog = new CatEmailLog();
        BeanUtils.copyProperties(catEmailLogDto,catEmailLog);
        catEmailLog.setCreateUserId(result.getCreateUserId());
        catEmailLog.setCreateTime(result.getCreateTime());
        catEmailLog.setUpdateUserId("liu");
        catEmailLog.setUpdateTime(new Date());
        catEmailLog.setVersion(result.getVersion()+1);
        return catEmailLogRepository.saveAndFlush(catEmailLog);
    }

    @Override
    public Integer deleteById(Integer id) throws Exception {
        if(null == id){
            throw new Exception("没有主键ID");
        }
        CatEmailLog result = catEmailLogRepository.getById(id);
        if(null == result){
            throw new Exception("主键ID错误");
        }
        catEmailLogRepository.deleteById(id);
        return 1;
    }
}
