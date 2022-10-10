package ru.otus.service;

/**
 * Интерфейс CheckAnswer
 */
public interface CheckAnswer {

    /**
     * Метод checkCorrectAnswer выполняет проверку правильности ответа по номеру теста
     *
     * @param testNumber
     * @param selectedAnswerId
     * @return true если ответ верный, false - если ответ не правильный
     */
    boolean checkCorrectAnswer(int testNumber, int selectedAnswerId);

    /**
     * Метод isCorrectInput проверяет корректность введенных данных из консоли
     *
     * @param selectedAnswerId
     * @param testNumber
     * @return
     */
    boolean isCorrectInput(int selectedAnswerId, int testNumber);


    /**
     * Метод testPassed определяет прохождение или не прохождение теста
     *
     * @param countCorrectAnswers
     * @return
     */
    boolean testPassed(int countCorrectAnswers);

}