package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.dao.QuestionDao;
import ru.otus.pojo.Question;

import java.util.stream.Collectors;

@Component
public class QuestionServiceImpl implements QuestionService {
    private final IOService ioService;
    private final QuestionDao questionDao;

    @Autowired
    public QuestionServiceImpl(IOService ioService, QuestionDao questionDao) {
        this.ioService = ioService;
        this.questionDao = questionDao;
    }

    @Override
    public int doPrintQuestionAndGetAnswers(int testNumber) {
        Question question = questionDao.getQuestionList().get(testNumber);
        return ioService.readIntWithPrompt("Question " + (testNumber + 1) + ":\n"
                + question.getListAnswer().stream()
                .map(a -> a.getId() + ") " + a.getTextAnswer()).collect(Collectors.joining("\n"))
                + "Enter 1, 2 or 3:");
    }
}
