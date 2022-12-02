package ru.otus.spring03testingstudentsnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring03testingstudentsnew.pojo.Question;
import ru.otus.spring03testingstudentsnew.pojo.Result;

@Service
public class ResultServiceImpl implements ResultService {
    private final int minNumberCorrectAnswers;
    private final Result result;

    @Autowired
    public ResultServiceImpl(@Value("${minNumberCorrectAnswers}") int minNumberCorrectAnswers, Result result) {
        this.minNumberCorrectAnswers = minNumberCorrectAnswers;
        this.result = result;
    }

    @Override
    public void saveAnswerResult(Question question, int selectedAnswerId) {
        if (question.getListAnswer().get(selectedAnswerId - 1).isRightAnswer()) {
            result.addTrueAnswer();
        }
    }

    @Override
    public String getResult() {
        return result.getCountTrueAnswer() >= minNumberCorrectAnswers ? "Test passed" : "Test failed, try again";
    }

}
