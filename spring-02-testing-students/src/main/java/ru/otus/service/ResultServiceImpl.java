package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.pojo.Result;

@Service
public class ResultServiceImpl implements ResultService {
    private final int minNumberCorrectAnswers;
    private final QuestionDao questionDao;

    @Autowired
    public ResultServiceImpl(@Value("${minNumberCorrectAnswers}") int minNumberCorrectAnswers, QuestionDao questionDao) {
        this.minNumberCorrectAnswers = minNumberCorrectAnswers;
        this.questionDao = questionDao;
    }

    @Override
    public void saveAnswerResult(int testNumber, int selectedAnswerId, Result result) {
        if (questionDao.getQuestionList().get(testNumber).getListAnswer().get(selectedAnswerId - 1).isRightAnswer()) {
            result.addTrueAnswer();
        }
    }

    @Override
    public String getResult(Result result) {
        return result.getCountTrueAnswer() >= minNumberCorrectAnswers ? "Test passed" : "Test failed, try again";
    }

}
