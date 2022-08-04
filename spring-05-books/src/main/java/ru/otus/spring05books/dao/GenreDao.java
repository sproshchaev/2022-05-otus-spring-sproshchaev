package ru.otus.spring05books.dao;

import ru.otus.spring05books.domain.Genre;

import java.util.List;

/**
 * Интерфейс DAO для класса Genre
 */
public interface GenreDao {

    /**
     * Метод createGenre создает новый жанр в библиотеке
     *
     * @param genre
     * @return
     */
    long createGenre(Genre genre);

    /**
     * Метод updateGenre обновляет сведения о жанре в библиотеке
     *
     * @param genre
     * @return
     */
    boolean updateGenre(Genre genre);

    /**
     * Метод deleteGenre удаляет сведения об жанре из библиотеки
     *
     * @param genre
     * @return
     */
    boolean deleteGenre(Genre genre);

    /**
     * Метод getGenreById формирует сведения о жанре по id
     *
     * @param id
     * @return
     */
    Genre getGenreById(long id);

    /**
     * Метод getIdByGenre возвращает id передаваемого жанра
     * @param genre
     * @return
     */
    long getIdByGenre(Genre genre);

    /**
     * Метод getAllGenres формирует сведения по всем жанрам из библиотеки
     *
     * @return
     */
    List<Genre> getAllGenres();

    /**
     * Метод getCountOfGenres возвращает число жанров, которые есть в библиотеке
     *
     * @return
     */
    int getCountOfGenres();

}
