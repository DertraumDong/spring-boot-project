package com.dtr.bean.dto;

import com.dtr.bean.InterviewQuestionRecord;
import lombok.Data;

import java.util.List;

/**
 * @author liudong
 * 2024/4/19 16:37
 * @version 1.0
 */
@Data
public class AnswerDto {

    // 面试记录
    private Long interviewRecordId;
    // 题目
    private List<InterviewQuestionRecord> list;

}
