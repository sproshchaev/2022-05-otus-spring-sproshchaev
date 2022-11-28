package ru.otus.service;

import ru.otus.pojo.Question;
import ru.otus.pojo.Result;

public interface ResultService {
    void saveAnswerResult(Question question, int selectedAnswerId, Result result);

    String getResult(Result result);
}
