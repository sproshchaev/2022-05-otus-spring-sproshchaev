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
    private final LanguageService languageService;

    @Autowired
    public ResultServiceImpl(@Value("${minNumberCorrectAnswers}") int minNumberCorrectAnswers, Result result,
                             LanguageService languageService) {
        this.minNumberCorrectAnswers = minNumberCorrectAnswers;
        this.result = result;
        this.languageService = languageService;
    }

    @Override
    public void saveAnswerResult(Question question, int selectedAnswerId) {
        if (question.getListAnswer().get(selectedAnswerId - 1).isRightAnswer()) {
            result.addTrueAnswer();
        }
    }

    @Override
    public String getResult() {
        return result.getCountTrueAnswer() >= minNumberCorrectAnswers
                ? languageService.getLocalString("result.passed")
                : languageService.getLocalString("result.failed");
    }

}
