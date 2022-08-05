package ru.otus.dao;

import ru.otus.pojo.Question;

/**
 * Интерфейс ReadingFile
 */
public interface ReadingFile {
    Question getQuestionById(int questionId);
}
