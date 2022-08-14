package ru.otus.spring08books.services;

import ru.otus.spring08books.entities.Genre;

/**
 * Интерфейс GenreService содержит методы для работы с сущностью Genre
 *
 * @see ru.otus.spring08books.entities.Genre
 */
public interface GenreService {

    /**
     * Метод createGenre создает новый жанр в библиотеке (Crud)
     *
     * @param name
     * @return
     */
    String createGenre(String name);

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
     * Метод updateGenre обновляет данные о жанре в библиотеке (crUd)
     * Метод изменяет данные
     *
     * @param id
     * @param name
     * @return
     */
    String updateGenre(long id, String name);

    /**
     * Метод deleteGenreById удаляет данные о жанре из библиотеки (cruD)
     * Метод изменяет данные
     *
     * @param id
     * @return
     */
    String deleteGenreById(long id);

    /**
     * Метод getFirstGenreByName возвращает первый жанр из списка с одинаковым значением поля name
     *
     * @param genreName
     * @return
     */
    Genre getFirstGenreByName(String genreName);

}
