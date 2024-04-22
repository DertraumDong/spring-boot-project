package com.dtr.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author liudong
 * 2024/4/19 13:01
 * @version 1.0
 */
@Data
@TableName("interview_question")
public class InterviewQuestion {
    // 面试题ID
    @TableId(type = IdType.AUTO)
    private Long id;
    // 题目内容
    private String questionText;
    // 正确答案
    private String correctAnswer;
    // 解析
    private String explanation;
    // 难度
    private Integer difficulty = 1;
}
