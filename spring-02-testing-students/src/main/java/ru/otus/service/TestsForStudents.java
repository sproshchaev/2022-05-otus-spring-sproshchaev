package ru.otus.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.dao.ReadingQuestionsFile;

import java.util.Scanner;

/**
 * Класс TestsForStudents содержит методы для тестирования студентов
 */
@Component
public class TestsForStudents implements Tests {
    private final int ANSWER_OPTION_ONE = 1;
    private final int ANSWER_OPTION_TWO = 2;
    private final int ANSWER_OPTION_THREE = 3;
    private final int TOTAL_QUESTIONS_IN_TEST = 5;
    private String studentsName;
    private int countCorrectAnswers;
    @Autowired
    ReadingQuestionsFile readingQuestionsFile;
    @Autowired
    IoСonsole ioСonsole;
    @Value("${numberCorrectAnswers}")
    int numberCorrectAnswers;

    /**
     * Метод runTest() выполняет тестирование студента
     */
    @Override
    public void runTest() {
        doPrintWelcomeAndWaitGetYouName();
        studentsName = readStudentsName();
        int selectedAnswerId;
        for (int testNumber = 1; testNumber <= TOTAL_QUESTIONS_IN_TEST; testNumber++) {
            doPrintQuestionAndAnswers(testNumber);
            selectedAnswerId = readAnswerNumber();
            if (сheckСorrectAnswer(testNumber, selectedAnswerId)) {
                countCorrectAnswers++;
            }
        }
        doPrintTestResults();
    }


    /**
     * Метод doWelcome выводит в консоль строку с приглашением тестирования
     */
    private void doPrintWelcomeAndWaitGetYouName() {
        ioСonsole.writeLnString("Testing on the basics of Java");
        ioСonsole.writeString("Please enter your name: ");
    }

    /**
     * Метод readStudentsName считывает имя студента из консоли
     *
     * @return student's name
     */
    private String readStudentsName() {
        return ioСonsole.readString();
    }

    /**
     * Метод readAnswerNumber считывает номер ответа из консоли
     *
     * @return answer number
     */
    private int readAnswerNumber() {
        int selectedAnswerId = 0;
        boolean inputIsCorrect = false;
        while (!inputIsCorrect) {
            doPrintExpectedInput();
            selectedAnswerId = ioСonsole.readInt();
            if (isCorrectInput(selectedAnswerId)) {
                inputIsCorrect = true;
            } else {
                doPrintInvalidInput();
            }
        }
        return selectedAnswerId;
    }

    /**
     * Метод doPrintExpectedInput() выводит ожидаемые для ввода данные от студента
     */
    private void doPrintExpectedInput() {
        ioСonsole.writeString("Enter " + ANSWER_OPTION_ONE + ", " + ANSWER_OPTION_TWO + " or " + ANSWER_OPTION_THREE + ": ");
    }

    /**
     * Метод doPrintInvalidInput выводит сообщение о том, что ввод данных выполнен неверно
     */
    private void doPrintInvalidInput() {
        ioСonsole.writeString("Incorrect input, try again.");
    }

    /**
     * Метод isCorrectInput проверяет корректность введенных данных из консоли
     *
     * @param selectedAnswerId
     * @return
     */
    private boolean isCorrectInput(int selectedAnswerId) {
        if ((selectedAnswerId == ANSWER_OPTION_ONE)
                || (selectedAnswerId == ANSWER_OPTION_TWO)
                || (selectedAnswerId == ANSWER_OPTION_THREE)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод сheckСorrectnessAnswer выполняет проверку правильности ответа по номеру теста
     *
     * @param testNumber
     * @param selectedAnswerId
     * @return true если ответ верный, false - если ответ не правильный
     */
    private boolean сheckСorrectAnswer(int testNumber, int selectedAnswerId) {
        if (selectedAnswerId == readingQuestionsFile.getQuestionById(testNumber).getRightAnswer()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Метод doPrintQuestionAndAnswers выводит в консоль вопрос теста, передаваемый ему в качестве аргумента
     * и три варианта ответа
     *
     * @param testNumber
     */
    private void doPrintQuestionAndAnswers(int testNumber) {
        ioСonsole.writeLnString(readingQuestionsFile.getQuestionById(testNumber).getQuestionText());
        ioСonsole.writeLnString(" " + ANSWER_OPTION_ONE + ") " + readingQuestionsFile.getQuestionById(testNumber).getFirstAnswer());
        ioСonsole.writeLnString(" " + ANSWER_OPTION_TWO + ") " + readingQuestionsFile.getQuestionById(testNumber).getSecondAnswer());
        ioСonsole.writeLnString(" " + ANSWER_OPTION_THREE + ") " + readingQuestionsFile.getQuestionById(testNumber).getThirdAnswer());
    }

    /**
     * Метод doPrintTestResults выводит результаты прохождения теста студентом
     */
    private void doPrintTestResults() {
        ioСonsole.writeLnString("Dear " + studentsName + ", your result: " + countCorrectAnswers + " out of " + TOTAL_QUESTIONS_IN_TEST);
        if (countCorrectAnswers >= numberCorrectAnswers) {
            ioСonsole.writeLnString("Test passed successfully!");
        } else {
            ioСonsole.writeLnString("Test failed!");
        }
    }
}
