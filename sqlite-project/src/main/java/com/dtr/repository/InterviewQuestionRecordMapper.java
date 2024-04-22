package com.dtr.repository;

/**
 * @author liudong
 * 2024/4/19 15:06
 * @version 1.0
 */
// InterviewQuestionRecordMapper.java

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dtr.bean.InterviewQuestionRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewQuestionRecordMapper extends BaseMapper<InterviewQuestionRecord> {
}

