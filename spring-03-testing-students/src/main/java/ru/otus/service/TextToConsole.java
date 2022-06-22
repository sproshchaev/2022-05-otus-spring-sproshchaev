package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.dao.ReadingQuestionsFile;

/**
 * Класс TextToConsole содержит методы вывода в консоль информации при проведении тестирования
 */
@Component
public class TextToConsole {
    // @Value("${answerOptionOne}")
    private int answerOptionOne;
    // @Value("${answerOptionTwo}")
    private int answerOptionTwo;
    // @Value("${answerOptionThree}")
    private int answerOptionThree;
    // @Value("${totalQuestionsInTest}")
    private int totalQuestionsInTest;
    //@Autowired
    IoСonsole ioСonsole;
    //@Autowired
    ReadingQuestionsFile readingQuestionsFile;
    //@Autowired
    CheckAnswer checkAnswer;

    /**
     * Конструктор класса с параметрами
     * @param answerOptionOne
     * @param answerOptionTwo
     * @param answerOptionThree
     * @param totalQuestionsInTest
     * @param ioСonsole
     * @param readingQuestionsFile
     * @param checkAnswer
     */
    @Autowired
    public TextToConsole(@Value("${answerOptionOne}") int answerOptionOne,
                         @Value("${answerOptionTwo}") int answerOptionTwo,
                         @Value("${answerOptionThree}") int answerOptionThree,
                         @Value("${totalQuestionsInTest}") int totalQuestionsInTest,
                         IoСonsole ioСonsole,
                         ReadingQuestionsFile readingQuestionsFile,
                         CheckAnswer checkAnswer) {
        this.answerOptionOne = answerOptionOne;
        this.answerOptionTwo = answerOptionTwo;
        this.answerOptionThree = answerOptionThree;
        this.totalQuestionsInTest = totalQuestionsInTest;
        this.ioСonsole = ioСonsole;
        this.readingQuestionsFile = readingQuestionsFile;
        this.checkAnswer = checkAnswer;
    }

    /**
     * Конструктор класса без параметров
     */
    public TextToConsole() {
    }

    /**
     * Метод doWelcome выводит в консоль строку с приглашением тестирования
     */
    public void doPrintWelcomeAndWaitGetYouName() {
        ioСonsole.writeLnString("Testing on the basics of Java");
        ioСonsole.writeString("Please enter your name: ");
    }

    /**
     * Метод doPrintQuestionAndAnswers выводит в консоль вопрос теста, передаваемый ему в качестве аргумента
     * и три варианта ответа
     *
     * @param testNumber
     */
    public void doPrintQuestionAndAnswers(int testNumber) {
        ioСonsole.writeLnString(readingQuestionsFile.getQuestionById(testNumber).getQuestionText());
        ioСonsole.writeLnString(" " + answerOptionOne + ") " + readingQuestionsFile.getQuestionById(testNumber).getFirstAnswer());
        ioСonsole.writeLnString(" " + answerOptionTwo + ") " + readingQuestionsFile.getQuestionById(testNumber).getSecondAnswer());
        ioСonsole.writeLnString(" " + answerOptionThree + ") " + readingQuestionsFile.getQuestionById(testNumber).getThirdAnswer());
    }

    /**
     * Метод doPrintExpectedInput() выводит ожидаемые для ввода данные от студента
     */
    public void doPrintExpectedInput() {
        ioСonsole.writeString("Enter " + answerOptionOne + ", " + answerOptionTwo + " or " + answerOptionThree + ": ");
    }

    /**
     * Метод doPrintInvalidInput выводит сообщение о том, что ввод данных выполнен неверно
     */
    public void doPrintInvalidInput() {
        ioСonsole.writeString("Incorrect input, try again.");
    }

    /**
     * Метод doPrintTestResults выводит результаты прохождения теста студентом
     */
    public void doPrintTestResults(String studentsName, int countCorrectAnswers) {
        ioСonsole.writeLnString("Dear " + studentsName + ", your result: " + countCorrectAnswers + " out of " + totalQuestionsInTest);
        if (checkAnswer.testPassed(countCorrectAnswers)) {
            ioСonsole.writeLnString("Test passed successfully!");
        } else {
            ioСonsole.writeLnString("Test failed!");
        }
    }

}
