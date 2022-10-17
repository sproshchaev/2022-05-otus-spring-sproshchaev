package ru.otus.spring18books.services;

/**
 * Интерфейс CommentService содержит методы для работы с сущностью Comment
 *
 * @see ru.otus.spring18books.domain.Comment
 */
public interface CommentService {

    /**
     * Число комментариев ко всем книгам в библиотеке, определяет популярность ресурса
     * @return
     */
    long countComments();

}