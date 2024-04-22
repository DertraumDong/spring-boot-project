package com.dtr.bean.dto;

import com.dtr.bean.InterviewQuestionRecord;
import com.dtr.bean.InterviewRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liudong
 * 2024/4/20 22:53
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterviewRecordDto implements Serializable {
    InterviewRecord interviewRecord;
    List<InterviewQuestionRecord> list;
}
