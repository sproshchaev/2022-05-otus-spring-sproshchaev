package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.otus.dao.ReadingQuestionsFile;

import java.util.Locale;

/**
 * Класс TextToConsole содержит методы вывода в консоль информации при проведении тестирования
 */
@Component
public class TextToConsole {
    private int answerOptionOne;
    private int answerOptionTwo;
    private int answerOptionThree;
    private int totalQuestionsInTest;
    private IoСonsole ioСonsole;
    private ReadingQuestionsFile readingQuestionsFile;
    private CheckAnswer checkAnswer;
    private ApplicationContext context;

    /**
     * Конструктор класса с параметрами
     *
     * @param answerOptionOne
     * @param answerOptionTwo
     * @param answerOptionThree
     * @param totalQuestionsInTest
     * @param ioСonsole
     * @param readingQuestionsFile
     * @param checkAnswer
     * @param context
     */
    @Autowired
    public TextToConsole(@Value("${answerOption.One}") int answerOptionOne,
                         @Value("${answerOption.Two}") int answerOptionTwo,
                         @Value("${answerOption.Three}") int answerOptionThree,
                         @Value("${totalQuestionsInTest}") int totalQuestionsInTest,
                         IoСonsole ioСonsole,
                         ReadingQuestionsFile readingQuestionsFile,
                         CheckAnswer checkAnswer,
                         ApplicationContext context) {
        this.answerOptionOne = answerOptionOne;
        this.answerOptionTwo = answerOptionTwo;
        this.answerOptionThree = answerOptionThree;
        this.totalQuestionsInTest = totalQuestionsInTest;
        this.ioСonsole = ioСonsole;
        this.readingQuestionsFile = readingQuestionsFile;
        this.checkAnswer = checkAnswer;
        this.context = context;
    }

    /**
     * Конструктор класса без параметров
     */
    public TextToConsole() {
    }

    /**
     * Метод doWelcome выводит в консоль строку с приглашением тестирования и локализацией текста
     */
    public void doPrintWelcomeAndWaitGetYouName() {
        ioСonsole.writeLnString(context.getMessage("heading.testsname", null, Locale.getDefault()));
        ioСonsole.writeString(context.getMessage("heading.welcome", null, Locale.getDefault()));
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
        ioСonsole.writeString(getLocalization("waitingresponse.enter") + " "
                + answerOptionOne + ", " + answerOptionTwo + " "
                + getLocalization("waitingresponse.or")
                + " " + answerOptionThree + ": ");
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
        ioСonsole.writeLnString(getLocalization("result.dear") + " " + studentsName + ", "
                + getLocalization("result.yourresult") + " " + countCorrectAnswers
                + " " + getLocalization("result.outof") + " " + totalQuestionsInTest);
        if (checkAnswer.testPassed(countCorrectAnswers)) {
            ioСonsole.writeLnString(getLocalization("result.passed"));
        } else {
            ioСonsole.writeLnString(getLocalization("result.failed"));
        }
    }

    /**
     * Метод getLocalization
     *
     * @param code
     * @return
     */
    private String getLocalization(String code) {
        return context.getMessage(code, null, Locale.getDefault());
    }

}
