package com.dtr.repository;

/**
 * @author liudong
 * 2024/4/19 15:06
 * @version 1.0
 */
// InterviewRecordMapper.java

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dtr.bean.InterviewRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRecordMapper extends BaseMapper<InterviewRecord> {
}

