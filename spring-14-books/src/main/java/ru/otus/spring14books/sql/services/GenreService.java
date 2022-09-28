package ru.otus.spring14books.sql.services;

import ru.otus.spring14books.sql.domain.Genre;

import java.util.List;

/**
 * Интерфейс GenreService содержит методы для работы с сущностью Genre
 *
 * @see ru.otus.spring14books.sql.domain.Genre
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
     * Метод getAllGenre() возвращает все Жанры
     * @return
     */
    List<Genre> getAllGenre();

    /**
     * Число жанров книг в библиотеке
     *
     * @return
     */
    long countGenres();

}
