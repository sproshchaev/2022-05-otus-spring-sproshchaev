package ru.otus.spring05books.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring05books.domain.Author;

import java.util.List;

/**
 * Класс AuthorDaoJdbc реализует интерфейс AuthorDao для JDBC
 */
@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final JdbcOperations jdbc;

    /**
     * Конструктор класса
     * @param jdbc
     */
    public AuthorDaoJdbc(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Создать автора в библиотеке
     *
     * @param author
     */
    @Override
    public void createAuthor(Author author) {

    }

    /**
     * Обновить сведения об авторе в библиотеке
     *
     * @param author
     */
    @Override
    public void updateAuthor(Author author) {

    }

    /**
     * Удалить сведения об авторе из библиотеки
     *
     * @param author
     */
    @Override
    public void deleteAuthor(Author author) {

    }

    /**
     * Получить сведения об авторе по Id
     */
    @Override
    public String getAuthorById() {
        return null;
    }

    /**
     * Получить сведения по всем авторам из библиотеки
     *
     * @return
     */
    @Override
    public List<Author> getAllAuthors() {
        return null;
    }

    /**
     * Получить число авторов, чьи сведения есть в библиотеке
     */
    @Override
    public long getCountOfAuthors() {
        return 0;
    }
}
