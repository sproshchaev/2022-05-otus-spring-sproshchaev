package ru.otus.spring14books.sql.services;

import ru.otus.spring14books.sql.domain.Comment;

import java.util.List;

/**
 * Интерфейс CommentService содержит методы для работы с сущностью Comment
 *
 * @see ru.otus.spring14books.sql.domain.Comment
 */
public interface CommentService {


    /**
     * Получить список всех комментариев ко всем книгам библиотеки
     * @return
     */
    List<Comment> getAllComment();

    /**
     * Число комментариев ко всем книгам в библиотеке, определяет популярность ресурса
     * @return
     */
    long countComments();

}