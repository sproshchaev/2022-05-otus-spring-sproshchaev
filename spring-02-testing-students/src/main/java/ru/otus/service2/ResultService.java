package ru.otus.service2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.pojo.Question;

import java.util.List;

@Service
public class ResultService {

    // Число правильных ответов
    private int countTrueAnswer;
    private final List<Question> questionList;

    @Autowired
    public ResultService(List<Question> questionList, QuestionDao questionDao) {
        this.questionList = questionDao.getQuestions();
    }

    // метод save the answer result
    public void saveAnswerResult(int testNumber, int selectedAnswerId) {
        countTrueAnswer++;
    }

}
