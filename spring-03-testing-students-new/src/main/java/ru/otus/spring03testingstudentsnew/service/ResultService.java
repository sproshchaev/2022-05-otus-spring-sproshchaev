package ru.otus.spring03testingstudentsnew.service;

import ru.otus.spring03testingstudentsnew.pojo.Question;
import ru.otus.spring03testingstudentsnew.pojo.Result;

public interface ResultService {
    void saveAnswerResult(Question question, int selectedAnswerId, Result result);

    String getResult(Result result);
}
