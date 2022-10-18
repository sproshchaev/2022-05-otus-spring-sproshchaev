package ru.otus.service2;

/**
 * Сервис ввода данных
 */
public interface InputService {

    int readInt();

    int readIntWithPrompt(String prompt);

    String readStringWithPrompt(String prompt);

}
