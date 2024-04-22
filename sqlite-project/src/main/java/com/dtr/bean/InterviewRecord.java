package com.dtr.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 面试记录
 * @author liudong
 * 2024/4/19 15:03
 * @version 1.0
 */
@Data
@TableName("interview_record")
public class InterviewRecord implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    // 用户id
    private Long userId;
    @TableField(exist = false)
    private String userName = "用户";
    @TableField(exist = false)
    private String evaluation = "暂无";
    @TableField(exist = false)
    private String summary = "暂无";
    // 开始时间
    private LocalDateTime startTime;
    // 结束时间
    private LocalDateTime endTime;
}

