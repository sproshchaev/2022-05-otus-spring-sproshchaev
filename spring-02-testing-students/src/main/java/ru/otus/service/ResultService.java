package ru.otus.service;

public interface ResultService {
    void saveAnswerResult(int testNumber, int selectedAnswerId);

    String getResult();
}
