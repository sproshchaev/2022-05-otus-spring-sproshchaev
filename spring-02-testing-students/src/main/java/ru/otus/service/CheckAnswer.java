package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.dao.ReadingQuestionsFile;

/**
 * Класс CheckAnswer содержит методы контроля ввода данных из консоли
 */
@Component
public class CheckAnswer {
    @Value("${answerOptionOne}")
    private int answerOptionOne;
    @Value("${answerOptionTwo}")
    private int answerOptionTwo;
    @Value("${answerOptionThree}")
    private int answerOptionThree;
    @Value("${numberCorrectAnswers}")
    private int numberCorrectAnswers;
    @Autowired
    ReadingQuestionsFile readingQuestionsFile;

    /**
     * Метод сheckСorrectnessAnswer выполняет проверку правильности ответа по номеру теста
     *
     * @param testNumber
     * @param selectedAnswerId
     * @return true если ответ верный, false - если ответ не правильный
     */
    public boolean сheckСorrectAnswer(int testNumber, int selectedAnswerId) {
        if (selectedAnswerId == readingQuestionsFile.getQuestionById(testNumber).getRightAnswer()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод isCorrectInput проверяет корректность введенных данных из консоли
     *
     * @param selectedAnswerId
     * @return
     */
    public boolean isCorrectInput(int selectedAnswerId) {
        if ((selectedAnswerId == answerOptionOne)
                || (selectedAnswerId == answerOptionTwo)
                || (selectedAnswerId == answerOptionThree)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод testPassed определяет прохождение или не прохождение теста
     *
     * @param countCorrectAnswers
     * @return
     */
    public boolean testPassed(int countCorrectAnswers) {
        if (countCorrectAnswers >= numberCorrectAnswers) {
            return true;
        } else {
            return false;
        }
    }

    public void setAnswerOptionOne(int answerOptionOne) {
        this.answerOptionOne = answerOptionOne;
    }

    public void setAnswerOptionTwo(int answerOptionTwo) {
        this.answerOptionTwo = answerOptionTwo;
    }

    public void setAnswerOptionThree(int answerOptionThree) {
        this.answerOptionThree = answerOptionThree;
    }

    public void setNumberCorrectAnswers(int numberCorrectAnswers) {
        this.numberCorrectAnswers = numberCorrectAnswers;
    }

    public void setReadingQuestionsFile(ReadingQuestionsFile readingQuestionsFile) {
        this.readingQuestionsFile = readingQuestionsFile;
    }
}
