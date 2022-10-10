package ru.otus.service;

/**
 * Интерфейс IOService
 */
public interface IoService {

    /**
     * Метод readInt считывает целочисленные значения из консоли
     *
     * @return int
     */
    int readInt();

    /**
     * Метод readString считывает строковые значения из консоли
     *
     * @return String
     */
    String readString();

    /**
     * Метод writeString выводит передаваемую строку в консоль
     *
     * @param string строка для вывода
     */
    void writeString(String string);

    /**
     * Метод writeLnString выводит передаваемую строку в консоль с последующим переводом строки
     *
     * @param string строка для вывода с переводом строки
     */
    void writeLnString(String string);

    /**
     * Метод readAnswerNumber считывает номер ответа из консоли
     *
     * @param testNumber номер теста
     * @return номер ответа из теста
     */
    int readAnswerNumber(int testNumber);

    /**
     * Метод doPrintExpectedInput() выводит ожидаемые для ввода данные от студента
     *
     * @param testNumber номер теста
     */
    void doPrintExpectedInput(int testNumber);

    /**
     * Метод doPrintInvalidInput выводит сообщение о том, что ввод данных выполнен неверно
     */
    void doPrintInvalidInput();

    /**
     * Метод doWelcome выводит в консоль строку с приглашением тестирования
     */
    void doPrintWelcomeAndWaitGetYouName();

    /**
     * Метод readStudentsName считывает имя студента из консоли
     *
     * @return имя студента
     */
    String readStudentsName();

    /**
     * Метод doPrintQuestionAndAnswers выводит в консоль вопрос теста, передаваемый ему в качестве аргумента
     * и три варианта ответа
     *
     * @param testNumber номер теста
     */
    void doPrintQuestionAndAnswers(int testNumber);

    /**
     * Метод doPrintTestResults выводит результаты прохождения теста студентом
     *
     * @param studentsName имя студента
     * @param countCorrectAnswers число правильных ответов
     */
    void doPrintTestResults(String studentsName, int countCorrectAnswers);

}
