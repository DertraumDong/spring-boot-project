package com.dtr.web;

import com.dtr.bean.InterviewQuestion;
import com.dtr.bean.InterviewQuestionRecord;
import com.dtr.bean.InterviewRecord;
import com.dtr.bean.dto.InterviewRecordDto;
import com.dtr.service.impl.InterviewQuestionRecordService;
import com.dtr.service.impl.InterviewQuestionService;
import com.dtr.service.impl.InterviewRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liudong
 * 2024/4/20 22:49
 * @version 1.0
 */
@RestController
@RequestMapping("/api/interview/record")
public class InterviewRecordController {

    @Autowired
    private InterviewRecordService interviewRecordService;

    @Autowired
    private InterviewQuestionService interviewQuestionService;

    @Autowired
    private InterviewQuestionRecordService interviewQuestionRecordService;

    @GetMapping()
    public List<InterviewRecord> getAllInterviewRecords() {
        return interviewRecordService.list();
    }


    @GetMapping("/{id}")
    public InterviewRecordDto getInterviewRecord(@PathVariable Long id) {
        InterviewRecord interviewRecord = interviewRecordService.getById(id);
        List<InterviewQuestionRecord> list = interviewQuestionRecordService.lambdaQuery().eq(InterviewQuestionRecord::getInterviewRecordId, id).list();
        for (InterviewQuestionRecord interviewQuestionRecord : list) {
            interviewQuestionRecord.setInterviewRecord(interviewRecord);
            Long questionId = interviewQuestionRecord.getQuestionId();
            InterviewQuestion question = interviewQuestionService.getById(questionId);
            interviewQuestionRecord.setInterviewQuestion(question);
        }
        return InterviewRecordDto.builder().interviewRecord(interviewRecord).list(list).build();
    }

}
