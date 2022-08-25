package ru.otus.spring09books.services;

/**
 * Интерфейс GenreService содержит методы для работы с сущностью Genre
 *
 * @see ru.otus.spring09books.domain.Genre
 */
public interface GenreService {



    /**
     * Число жанров книг в библиотеке
     * @return
     */
    long countGenres();

}