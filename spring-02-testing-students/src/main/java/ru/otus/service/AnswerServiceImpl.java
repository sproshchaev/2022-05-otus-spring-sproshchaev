package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;

/**
 * Класс AnswerServiceImpl содержит методы контроля ввода данных из консоли
 */
@Service
public class AnswerServiceImpl implements AnswerService {
    private final int numberCorrectAnswers;
    private final QuestionDao questionDao;

    @Autowired
    public AnswerServiceImpl(@Value("${numberCorrectAnswers}") int numberCorrectAnswers, QuestionDao questionDao) {
        this.numberCorrectAnswers = numberCorrectAnswers;
        this.questionDao = questionDao;
    }

    /**
     * Метод checkCorrectAnswer выполняет проверку правильности ответа по номеру теста
     *
     * @param testNumber
     * @param selectedAnswerId
     * @return true если ответ верный, false - если ответ не правильный
     */
    public boolean checkCorrectAnswer(int testNumber, int selectedAnswerId) {
        return true; // todo убрать заглушку //selectedAnswerId == questionDao.getQuestionById(testNumber).getRightAnswer();
    }

    /**
     * Метод isCorrectInput проверяет корректность введенных данных из консоли
     *
     * @param selectedAnswerId
     * @param testNumber
     * @return
     */
    public boolean isCorrectInput(int selectedAnswerId, int testNumber) {
        //int countAnswer = questionDao.getQuestionById(testNumber).getListAnswer().size();
        return true; // todo убрать заглушку  // (selectedAnswerId > 0) && (selectedAnswerId <= countAnswer);
    }

    /**
     * Метод testPassed определяет прохождение или не прохождение теста
     *
     * @param countCorrectAnswers
     * @return
     */
    public boolean testPassed(int countCorrectAnswers) {
        return countCorrectAnswers >= numberCorrectAnswers;
    }

}
