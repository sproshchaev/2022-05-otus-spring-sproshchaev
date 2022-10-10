package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.Reading;

/**
 * Класс CheckAnswerImpl содержит методы контроля ввода данных из консоли
 */
@Service
public class CheckAnswerImpl implements CheckAnswer {
    private final int numberCorrectAnswers;
    private final Reading reading;

    @Autowired
    public CheckAnswerImpl(@Value("${numberCorrectAnswers}") int numberCorrectAnswers, Reading reading) {
        this.numberCorrectAnswers = numberCorrectAnswers;
        this.reading = reading;
    }

    /**
     * Метод checkCorrectAnswer выполняет проверку правильности ответа по номеру теста
     *
     * @param testNumber
     * @param selectedAnswerId
     * @return true если ответ верный, false - если ответ не правильный
     */
    public boolean checkCorrectAnswer(int testNumber, int selectedAnswerId) {
        return selectedAnswerId == reading.getQuestionById(testNumber).getRightAnswer();
    }

    /**
     * Метод isCorrectInput проверяет корректность введенных данных из консоли
     *
     * @param selectedAnswerId
     * @param testNumber
     * @return
     */
    public boolean isCorrectInput(int selectedAnswerId, int testNumber) {
        int countAnswer = reading.getQuestionById(testNumber).getListAnswer().size();
        return (selectedAnswerId > 0) && (selectedAnswerId <= countAnswer);
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
