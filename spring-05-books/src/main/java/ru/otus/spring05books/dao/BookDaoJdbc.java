package ru.otus.spring05books.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class BookDaoJdbc implements BookDao {

    private final JdbcOperations jdbc;

    /**
     * Конструктор класса
     * @param jdbc
     */
    public BookDaoJdbc(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Создать книгу
     */
    @Override
    public void createBook() {

    }

    /**
     * Обновить книгу
     */
    @Override
    public void updateBook() {

    }

    /**
     * Удалить книгу
     */
    @Override
    public void deleteBook() {

    }

    /**
     * Получить книгу по Id
     */
    @Override
    public String getBookById() {
        return null;
    }

    /**
     * Получить все книги
     *
     * @return
     */
    @Override
    public String getAllBooks() {
        return null;
    }
}
