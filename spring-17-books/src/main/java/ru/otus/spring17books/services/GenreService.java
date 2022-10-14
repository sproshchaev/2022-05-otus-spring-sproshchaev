package ru.otus.spring17books.services;

import ru.otus.spring17books.domain.Genre;

/**
 * Интерфейс GenreService содержит методы для работы с сущностью Genre
 *
 * @see ru.otus.spring17books.domain.Genre
 */
public interface GenreService {

    /**
     * Метод getFirstGenreByName возвращает первый жанр из списка с одинаковым значением поля name
     * Метод не изменяет данные
     *
     * @param genreName
     * @return
     */
    Genre getFirstGenreByName(String genreName);

    /**
     * Число жанров книг в библиотеке
     *
     * @return
     */
    long countGenres();

}