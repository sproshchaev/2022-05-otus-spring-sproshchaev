package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;

@Service
public class ResultServiceImpl implements ResultService {
    private final int minNumberCorrectAnswers;
    private int countTrueAnswer;
    private final QuestionDao questionDao;

    @Autowired
    public ResultServiceImpl(@Value("${minNumberCorrectAnswers}") int minNumberCorrectAnswers, QuestionDao questionDao) {
        this.minNumberCorrectAnswers = minNumberCorrectAnswers;
        this.questionDao = questionDao;
    }

    @Override
    public void saveAnswerResult(int testNumber, int selectedAnswerId) {
        if (questionDao.getQuestionList().get(testNumber).getListAnswer().get(selectedAnswerId - 1).isRightAnswer()) {
            countTrueAnswer++;
        }
    }

    @Override
    public String getResult() {
        return countTrueAnswer >= minNumberCorrectAnswers ? "Test passed" : "Test failed, try again";
    }

}
