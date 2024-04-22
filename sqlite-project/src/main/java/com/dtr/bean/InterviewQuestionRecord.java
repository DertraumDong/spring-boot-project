package com.dtr.bean;

/**
 * @author liudong
 * 2024/4/19 15:04
 * @version 1.0
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("interview_question_record")
public class InterviewQuestionRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    // 面试记录
    private Long interviewRecordId;

    @TableField(exist = false)
    private InterviewRecord interviewRecord;

    // 问题id
    private Long questionId;

    @TableField(exist = false)
    private InterviewQuestion interviewQuestion;

    // 用户回答
    private String userAnswer;
}

