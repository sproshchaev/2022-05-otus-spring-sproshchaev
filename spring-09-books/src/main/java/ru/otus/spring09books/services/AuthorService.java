package ru.otus.spring09books.services;

/**
 * Интерфейс AuthorService содержит методы для сущности Author
 *
 * @see ru.otus.spring09books.domain.Author
 */
public interface AuthorService {

    /**
     * Число авторов в библиотеке
     * @return
     */
    long countAuthors();
}