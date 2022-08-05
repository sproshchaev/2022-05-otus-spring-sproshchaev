package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.ReadingQuestionsFile;

/**
 * Класс CheckAnswer содержит методы контроля ввода данных из консоли
 */
@Service
public class CheckAnswer {
    private int answerOptionOne;
    private int answerOptionTwo;
    private int answerOptionThree;
    private int numberCorrectAnswers;
    private ReadingQuestionsFile readingQuestionsFile;

    /**
     * Конструктор класса с параметрами
     *
     * @param readingQuestionsFile
     */
    @Autowired
    public CheckAnswer(@Value("${answerOption.One}") String answerOptionOne,
                       @Value("${answerOption.Two}") String answerOptionTwo,
                       @Value("${answerOption.Three}") String answerOptionThree,
                       @Value("${numberCorrectAnswers}") String numberCorrectAnswers,
                       ReadingQuestionsFile readingQuestionsFile) {
        this.answerOptionOne = Integer.parseInt(answerOptionOne);
        this.answerOptionTwo = Integer.parseInt(answerOptionTwo);
        this.answerOptionThree = Integer.parseInt(answerOptionThree);
        this.numberCorrectAnswers = Integer.parseInt(numberCorrectAnswers);
        this.readingQuestionsFile = readingQuestionsFile;
    }

    /**
     * Конструктор класса без параметров
     */
    public CheckAnswer() {
    }

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
     * Метод isCorrectInput проверяет корректность введенных данных из консоли при ответе на вопрос теста
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
     * Метод isCorrectInputLanguage проверяет корректность ответа по выбору языка
     *
     * @param selectedAnswerId
     * @return
     */
    public boolean isCorrectInputLanguage(int selectedAnswerId) {
        if ((selectedAnswerId == 1)
                || (selectedAnswerId == 2)) {
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