package ru.otus.spring05books.dao;

import org.springframework.jdbc.core.JdbcOperations;
import ru.otus.spring05books.domain.Genre;

import java.util.List;

/**
 * Класс GenreDaoJdbc реализует интерфейс GenreDao для JDBC
 */
public class GenreDaoJdbc implements GenreDao {
    private final JdbcOperations jdbc;

    /**
     * Конструктор класса
     *
     * @param jdbc
     */
    public GenreDaoJdbc(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Создать внести новый жанр в библиотеку
     *
     * @param genre
     */
    @Override
    public void createGenre(Genre genre) {

    }

    /**
     * Обновить сведения о жанре
     *
     * @param genre
     */
    @Override
    public void updateGenre(Genre genre) {

    }

    /**
     * Удалить сведения об жанре из библиотеки
     *
     * @param genre
     */
    @Override
    public void deleteGenre(Genre genre) {

    }

    /**
     * Получить сведения о жанре по id
     */
    @Override
    public String getGenreById(long id) {
        return null;
    }

    /**
     * Получить сведения по всем жанрам из библиотеки
     *
     * @return
     */
    @Override
    public List<Genre> getAllGenres() {
        return null;
    }

    /**
     * Получить число жанров, которые есть в библиотеке
     */
    @Override
    public long getCountOfGenres() {
        return 0;
    }
}
