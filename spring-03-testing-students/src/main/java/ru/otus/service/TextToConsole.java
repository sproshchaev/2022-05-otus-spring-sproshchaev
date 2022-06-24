package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.otus.dao.ReadingQuestionsFile;
import ru.otus.pojo.Language;
import ru.otus.pojo.Student;

import java.util.Locale;

/**
 * Класс TextToConsole содержит методы вывода в консоль информации при проведении тестирования
 */
@Service
public class TextToConsole {
    private int answerOptionOne;
    private int answerOptionTwo;
    private int answerOptionThree;
    private int totalQuestionsInTest;
    private IoСonsole ioСonsole;
    private ReadingQuestionsFile readingQuestionsFile;
    private CheckAnswer checkAnswer;
    private ApplicationContext context;
    private Language language;
    private Student student;

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
                         ApplicationContext context,
                         Language language,
                         Student student) {
        this.answerOptionOne = answerOptionOne;
        this.answerOptionTwo = answerOptionTwo;
        this.answerOptionThree = answerOptionThree;
        this.totalQuestionsInTest = totalQuestionsInTest;
        this.ioСonsole = ioСonsole;
        this.readingQuestionsFile = readingQuestionsFile;
        this.checkAnswer = checkAnswer;
        this.context = context;
        this.language = language;
        this.student = student;
    }

    /**
     * Конструктор класса без параметров
     */
    public TextToConsole() {
    }

    /**
     * Метод doPrintSelectLanguage выводит сообщение о выборе языка
     */
    public void doPrintSelectLanguage() {
        ioСonsole.writeLnString("Please select a language 1-English, 2-Russian:");
    }

    /**
     * Метод doWelcome выводит в консоль строку с приглашением тестирования и локализацией текста
     */
    public void doPrintWelcomeAndWaitGetYouName() {
        ioСonsole.writeLnString(context.getMessage("heading.testsname", null, Locale.forLanguageTag(language.getIsoCode().toString())));
        ioСonsole.writeString(context.getMessage("heading.welcome", null, Locale.forLanguageTag(language.getIsoCode().toString())) + " ");
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
        ioСonsole.writeString(getLocalString("waitingresponse.enter") + " " + answerOptionOne + ", "
                + answerOptionTwo + " " + " " + answerOptionThree + ": ");
    }

    /**
     * Метод doPrintExpectedInput() выводит ожидаемые для ввода данные от студента (язык)
     */
    public void doPrintExpectedInput2() {
        ioСonsole.writeString("Please enter " + 1 + ", " + 2 + ": ");
    }


    /**
     * Метод doPrintInvalidInput выводит сообщение о том, что ввод данных выполнен неверно
     */
    public void doPrintInvalidInput() {
        ioСonsole.writeString(getLocalString("waitingresponse.incorrectinput"));
    }

    /**
     * Метод doPrintTestResults выводит результаты прохождения теста студентом
     */
//    public void doPrintTestResults() {
    public void doPrintTestResults(int countCorrectAnswers) {
        ioСonsole.writeLnString(getLocalString("result.dear") + " " + student.getName() + ", "
                + getLocalString("result.yourresult") + " " + countCorrectAnswers
                + " " + getLocalString("result.outof") + " " + totalQuestionsInTest);
        if (checkAnswer.testPassed(countCorrectAnswers)) {
            ioСonsole.writeLnString(getLocalString("result.passed"));
        } else {
            ioСonsole.writeLnString(getLocalString("result.failed"));
        }
    }

    /**
     * Метод getLocalString возвращает локализованную строку для вывода в консоль
     *
     * @param code
     * @return
     */
    private String getLocalString(String code) {
        return context.getMessage(code, null, Locale.forLanguageTag(language.getIsoCode().toString()));
    }

}
