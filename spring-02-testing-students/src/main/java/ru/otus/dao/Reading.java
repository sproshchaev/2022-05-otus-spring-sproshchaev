package ru.otus.dao;

import ru.otus.pojo.Question;

import java.util.List;

/**
 * Интерфейс Reading
 */
public interface Reading {

    /**
     * Метод fillQuestionList читает из ресурса файл с вопросами
     * @return
     */
    List<Question> fillQuestionList();

    /**
     * Метод getQuestionById возвращает экземпляр класса Question с заданным questionId
     *
     * @param questionId
     * @return
     */
    Question getQuestionById(int questionId);

}
