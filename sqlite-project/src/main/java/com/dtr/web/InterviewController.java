package com.dtr.web;


import com.dtr.bean.InterviewQuestion;
import com.dtr.bean.InterviewQuestionRecord;
import com.dtr.bean.InterviewRecord;
import com.dtr.bean.constant.InterviewConstant;
import com.dtr.bean.dto.AnswerDto;
import com.dtr.bean.dto.IdDto;
import com.dtr.service.impl.InterviewQuestionRecordService;
import com.dtr.service.impl.InterviewQuestionService;
import com.dtr.service.impl.InterviewRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liudong
 * 2024/4/19 15:14
 * @version 1.0
 */
@RestController
@RequestMapping("/api/interview")
public class InterviewController {
    @Autowired
    private InterviewRecordService interviewRecordService;

    @Autowired
    private InterviewQuestionService interviewQuestionService;

    @Autowired
    private InterviewQuestionRecordService interviewQuestionRecordService;

    @PostMapping("/start")
    public InterviewRecord startInterview() {
        InterviewRecord interviewRecord = new InterviewRecord();
        interviewRecord.setStartTime(LocalDateTime.now());
        interviewRecord.setUserId(InterviewConstant.test_user_id);
        interviewRecordService.save(interviewRecord);
        return interviewRecord;
    }

    @GetMapping("/questions")
    public List<InterviewQuestion> getInterviewQuestions(
            @RequestParam(required = false) Integer difficulty,
            @RequestParam(required = false) Integer count
    ) {
        if (difficulty != null) {
            if (count != null) {
                return interviewQuestionService.getRandomQuestionsByDifficulty(difficulty, count);
            } else {
                return interviewQuestionService.getQuestionsByDifficulty(difficulty);
            }
        } else {
            return interviewQuestionService.list();
        }
    }

    @PostMapping("/answer")
    public void answerQuestion(@RequestBody AnswerDto answerDto) {
        List<InterviewQuestionRecord> list = answerDto.getList();
        for (InterviewQuestionRecord interviewQuestionRecord : list) {
            interviewQuestionRecordService.saveOrUpdate(interviewQuestionRecord);
        }

    }

    @PostMapping("/end")
    public void endInterview(@RequestBody @Valid IdDto idDto) {
        Long interviewRecordId = idDto.getId();
        InterviewRecord interviewRecord = interviewRecordService.getById(interviewRecordId);
        interviewRecord.setEndTime(LocalDateTime.now());
        interviewRecordService.updateById(interviewRecord);
    }
}

