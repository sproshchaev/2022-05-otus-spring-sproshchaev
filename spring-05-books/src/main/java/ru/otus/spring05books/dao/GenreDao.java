package ru.otus.spring05books.dao;

import ru.otus.spring05books.domain.Genre;

import java.util.List;

/**
 * Интерфейс DAO для класса Genre
 */
public interface GenreDao {

    /**
     * Создать внести новый жанр в библиотеку
     */
    void createGenre(Genre genre);

    /**
     * Обновить сведения о жанре
     */
    void updateGenre(Genre genre);

    /**
     * Удалить сведения об жанре из библиотеки
     */
    void deleteGenre(Genre genre);

    /**
     * Получить сведения о жанре по id
     */
    String getGenreById(long id);

    /**
     * Получить сведения по всем жанрам из библиотеки
     *
     * @return
     */
    List<Genre> getAllGenres();

    /**
     * Получить число жанров, которые есть в библиотеке
     */
    long getCountOfGenres();

}
