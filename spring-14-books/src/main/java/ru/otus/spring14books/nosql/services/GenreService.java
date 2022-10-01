package ru.otus.spring14books.nosql.services;

import ru.otus.spring14books.nosql.domain.Genre;

import java.util.List;

/**
 * Интерфейс GenreService содержит методы для работы с сущностью Genre
 *
 * @see ru.otus.spring14books.nosql.domain.Genre
 */
public interface GenreService {

    /**
     * Метод createGenreByName создает новый жанр в библиотеке (Crud)
     *
     * @param name
     * @return
     */
    String createGenreByName(String name);

    /**
     * Метод createGenreByName создает новый жанр в библиотеке (Crud)
     *
     * @param genre
     */
    void createGenre(Genre genre);


    /**
     * Метод getIdByGenre возвращает информацию о результатах поиска жанра с данным id в библиотеке
     * Метод не изменяет данные
     *
     * @param name
     * @return
     */
    String getIdByGenre(String name);

    /**
     * Метод getGenreById получает данные о жанре по его id (cRud)
     * Метод не изменяет данные
     *
     * @param id
     * @return
     */
    String getGenreById(String id);

    /**
     * Метод getAllGenres получает список всех жанров из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    String getAllGenres();

    /**
     * Метод getAllGenresList получает коллекцию всех жанров из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    List<Genre> getAllGenresList();

    /**
     * Метод updateGenre обновляет данные о жанре в библиотеке (crUd)
     * Метод изменяет данные
     *
     * @param id
     * @param name
     * @return
     */
    String updateGenre(String id, String name);

    /**
     * Метод deleteGenreById удаляет данные о жанре из библиотеки (cruD)
     * Метод изменяет данные
     *
     * @param id
     * @return
     */
    String deleteGenreById(String id);

    /**
     * Метод getFirstGenreByName возвращает первый жанр из списка с одинаковым значением поля name
     *
     * @param genreName
     * @return
     */
    Genre getFirstGenreByName(String genreName);

    /**
     * Метод countGenres возвращает число жанров
     *
     * @return
     */
    Long countGenres();
}
