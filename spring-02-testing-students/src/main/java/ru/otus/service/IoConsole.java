package ru.otus.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.Reading;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Класс IoConsole осуществляет вывод текста в консоль, чтение данных из консоли
 */
@Service
public class IoConsole implements IoService {
    private final PrintStream output;
    private final Scanner input;
    private final CheckAnswer checkAnswer;
    private final Reading reading;
    private final int totalQuestionsInTest;

    public IoConsole(CheckAnswer checkAnswer, Reading reading,
                     @Value("${totalQuestionsInTest}") int totalQuestionsInTest) {
        this.checkAnswer = checkAnswer;
        this.reading = reading;
        this.output = System.out;
        this.input = new Scanner(System.in);
        this.totalQuestionsInTest = totalQuestionsInTest;
    }

    /**
     * Метод readInt считывает целочисленные значения из консоли
     *
     * @return введенное целочисленное значение в консоли
     */
    @Override
    public int readInt() {
        return Integer.parseInt(input.next());
    }

    /**
     * Метод readString считывает строковые значения из консоли
     *
     * @return введенная строка в консоли
     */
    @Override
    public String readString() {
        return input.nextLine();
    }

    /**
     * Метод writeString выводит передаваемую строку в консоль
     *
     * @param string строка для вывода в консоль
     */
    @Override
    public void writeString(String string) {
        output.print(string);
    }

    /**
     * Метод writeLnString выводит передаваемую строку в консоль с последующим переводом строки
     *
     * @param string строка для выводя в консоль с переводом строки
     */
    @Override
    public void writeLnString(String string) {
        output.println(string);
    }

    /**
     * Метод readAnswerNumber считывает номер ответа из консоли
     *
     * @param testNumber номер теста
     * @return номер введенного ответа
     */
    public int readAnswerNumber(int testNumber) {
        int selectedAnswerId = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            doPrintExpectedInput(testNumber);
            selectedAnswerId = readInt();
            if (checkAnswer.isCorrectInput(selectedAnswerId, testNumber)) {
                isValidInput = true;
            } else {
                doPrintInvalidInput();
            }
        }
        return selectedAnswerId;
    }

    /**
     * Метод doPrintExpectedInput() выводит ожидаемые для ввода данные от студента
     *
     * @param testNumber номер теста
     */
    public void doPrintExpectedInput(int testNumber) {
        int countQuestions = reading.getQuestionById(testNumber).getListAnswer().size();
        writeString("Enter ");
        for (int i = 0; i < countQuestions; i++) {
            writeString((i + 1) + getSeparator(i, countQuestions));
        }
        writeString(": ");
    }

    /**
     * Метод getSeparator возвращает разделитель ', ' или ' or ' при перечислении вариантов возможных ответов
     * Пример: "Enter 1, 2 or 3:"
     *
     * @param currItem       текущий номер ответа
     * @param countQuestions всего ответов в вопросе
     * @return
     */
    private String getSeparator(int currItem, int countQuestions) {
        String separator = "";
        if (currItem < (countQuestions - 1)) {
            separator = ", ";
        }
        if (currItem == (countQuestions - 2)) {
            separator = " or ";
        }
        return separator;
    }

    /**
     * Метод doPrintInvalidInput выводит сообщение о том, что ввод данных выполнен неверно
     */
    public void doPrintInvalidInput() {
        writeString("Incorrect input, try again.");
    }

    /**
     * Метод doWelcome выводит в консоль строку с приглашением тестирования
     */
    public void doPrintWelcomeAndWaitGetYouName() {
        writeLnString("Testing on the basics of Java");
        writeString("Please enter your name: ");
    }

    /**
     * Метод readStudentsName считывает имя студента из консоли
     *
     * @return имя студента, проходящего тест
     */
    public String readStudentsName() {
        return readString();
    }

    /**
     * Метод doPrintQuestionAndAnswers выводит в консоль вопрос теста, передаваемый ему в качестве аргумента
     * и три варианта ответа
     *
     * @param testNumber номер теста
     */
    public void doPrintQuestionAndAnswers(int testNumber) {
        writeLnString(reading.getQuestionById(testNumber).getQuestionText());
        for (int i = 0; i < reading.getQuestionById(testNumber).getListAnswer().size(); i++) {
            writeLnString(" " + (i + 1) + ") " + reading.getQuestionById(testNumber).getListAnswer().get(i));
        }
    }

    /**
     * Метод doPrintTestResults выводит результаты прохождения теста студентом
     *
     * @param studentsName        имя студента
     * @param countCorrectAnswers число правильных ответов
     */
    public void doPrintTestResults(String studentsName, int countCorrectAnswers) {
        writeLnString("Dear " + studentsName + ", your result: " + countCorrectAnswers + " out of "
                + totalQuestionsInTest);
        if (checkAnswer.testPassed(countCorrectAnswers)) {
            writeLnString("Test passed successfully!");
        } else {
            writeLnString("Test failed!");
        }
    }
}
