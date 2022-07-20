package ru.otus.spring05books.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring05books.domain.Book;

/**
 *
 */
@Repository
public class BookDaoJdbc implements BookDao {

    private final JdbcOperations jdbc;

    /**
     * Конструктор класса
     *
     * @param jdbc
     */
    public BookDaoJdbc(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Метод createBook создает книгу
     */
    @Override
    public void createBook(Book book) {
        jdbc.update("insert into book(title, author_id, genre_id) values (?, ?, ?)", book.getTitle(), 1, 1);
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

    /**
     * Получить число книг
     */
    @Override
    public Long getCountOfBook() {
        Long countsOfBook = jdbc.queryForObject("select count(*) from book", Long.class);
        return countsOfBook;
    }
}
