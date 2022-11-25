package ru.otus.service;

import ru.otus.pojo.Result;

public interface ResultService {
    void saveAnswerResult(int testNumber, int selectedAnswerId, Result result);

    String getResult(Result result);
}
