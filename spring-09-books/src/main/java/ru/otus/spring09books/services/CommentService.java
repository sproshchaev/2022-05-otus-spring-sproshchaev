package ru.otus.spring09books.services;

/**
 * Интерфейс CommentService содержит методы для сущности Comment
 *
 * @see ru.otus.spring09books.domain.Comment
 */
public interface CommentService {

    /**
     * Число комментариев ко всем книгам в библиотеке, определяет популярность ресурса
     * @return
     */
    long countComments();
}