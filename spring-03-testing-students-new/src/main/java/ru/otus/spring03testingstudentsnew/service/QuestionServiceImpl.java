package ru.otus.spring03testingstudentsnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring03testingstudentsnew.dao.QuestionDao;
import ru.otus.spring03testingstudentsnew.pojo.Question;
import ru.otus.spring03testingstudentsnew.pojo.Result;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionServiceImpl implements QuestionService {
    private final IOService ioService;
    private final QuestionDao questionDao;
    private final ResultService resultService;

    @Autowired
    public QuestionServiceImpl(IOService ioService, QuestionDao questionDao, ResultService resultService) {
        this.ioService = ioService;
        this.questionDao = questionDao;
        this.resultService = resultService;
    }

    @Override
    public void doPrintQuestionAndGetAnswers() {
        List<Question> questionList = questionDao.getQuestionList();
        int selectedAnswerId;
        for (Question question: questionList) {
            selectedAnswerId = ioService.readIntWithPrompt("Question " + (question.getId()) + ":\n"
                    + question.getListAnswer().stream()
                    .map(a -> a.getId() + ") " + a.getTextAnswer()).collect(Collectors.joining("\n"))
                    + "\nEnter 1, 2 or ... " + question.getListAnswer().size() + ":");
            resultService.saveAnswerResult(question, selectedAnswerId);
        }
    }

}
