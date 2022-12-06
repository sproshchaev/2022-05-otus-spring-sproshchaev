package ru.otus.spring03testingstudentsnew.dao;

import ru.otus.spring03testingstudentsnew.pojo.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getQuestionList();

    void setFileCsvNameCurrent(int languageId);
}
