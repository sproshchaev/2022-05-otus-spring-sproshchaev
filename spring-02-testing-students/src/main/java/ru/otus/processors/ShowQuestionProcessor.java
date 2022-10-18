package ru.otus.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.dao.QuestionDao;
import ru.otus.pojo.Question;
import ru.otus.service2.IOService;

import java.util.List;

@Component
public class ShowQuestionProcessor {

    private final IOService ioService;

    private final List<Question> questionList;

    @Autowired
    public ShowQuestionProcessor(IOService ioService, QuestionDao questionDao) {
        this.ioService = ioService;
        this.questionList = questionDao.getQuestions();
    }

    public int doPrintQuestionAndAnswers(int testNumber) {
        return ioService.readIntWithPrompt("Question " + (testNumber + 1) + ": "
                + questionList.get(testNumber).getTextQuestion() + "\n"
                + "1) " + questionList.get(testNumber).getListAnswer().get(0).getTextAnswer() + "\n"
                + "2) " + questionList.get(testNumber).getListAnswer().get(1).getTextAnswer() + "\n"
                + "3) " + questionList.get(testNumber).getListAnswer().get(2).getTextAnswer() + "\n"
                + "Enter 1, 2 or 3:"
        );
    }
}
