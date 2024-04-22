package com.dtr.web;

/**
 * @author liudong
 * 2024/4/19 13:04
 * @version 1.0
 */

import com.dtr.bean.InterviewQuestion;
import com.dtr.service.impl.InterviewQuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class InterviewQuestionController {
    @Resource
    private InterviewQuestionService interviewQuestionService;

    @GetMapping
    public List<InterviewQuestion> getAllQuestions() {
        return interviewQuestionService.list();
    }

    @GetMapping("/{id}")
    public InterviewQuestion getQuestionById(@PathVariable Long id) {
        return interviewQuestionService.getById(id);
    }

    @PostMapping
    public void addQuestion(@RequestBody InterviewQuestion question) {
        interviewQuestionService.save(question);
    }

    @PutMapping("/{id}")
    public void updateQuestion(@PathVariable Long id, @RequestBody InterviewQuestion question) {
        question.setId(id);
        interviewQuestionService.updateById(question);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        interviewQuestionService.removeById(id);
    }
}
