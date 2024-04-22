package com.dtr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dtr.bean.InterviewQuestion;
import com.dtr.repository.InterviewQuestionMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author liudong
 * 2024/4/19 13:02
 * @version 1.0
 */
@Service
public class InterviewQuestionService extends ServiceImpl<InterviewQuestionMapper, InterviewQuestion> {

    public List<InterviewQuestion> getQuestionsByDifficulty(int difficulty) {
        return this.baseMapper.selectList(null).stream()
                .filter(question -> question.getDifficulty() == difficulty)
                .collect(Collectors.toList());
    }

    public List<InterviewQuestion> getRandomQuestionsByDifficulty(int difficulty, int count) {
        List<InterviewQuestion> questions = getQuestionsByDifficulty(difficulty);
        if (questions.size() <= count) {
            return questions;
        } else {
            Random random = new Random();
            return random.ints(0, questions.size())
                    .distinct()
                    .limit(count)
                    .mapToObj(questions::get)
                    .collect(Collectors.toList());
        }
    }
}
