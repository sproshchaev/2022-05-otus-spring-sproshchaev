package ru.otus.spring03testingstudentsnew.service;

import ru.otus.spring03testingstudentsnew.pojo.Question;

public interface ResultService {
    void saveAnswerResult(Question question, int selectedAnswerId);

    String getResult();
}
