package ru.otus.spring05books.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring05books.domain.Book;

import java.util.List;

/**
 * Класс BookDaoJdbc реализует интерфейс BookDao для JDBC
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
     * Создать новую книгу
     *
     * @param book
     * @return
     */
    @Override
    public long createBook(Book book) {
        // Создали в БД
        jdbc.update("insert into book(title, author_id, genre_id) values (?, ?, ?)", book.getTitle(), 1, 1);
        // Получили id этой книги
        // Внесли через сеттер в экземпляр этой книги
        return 0;
    }

    /**
     * Обновить сведения о книге
     *
     * @param book
     */
    @Override
    public void updateBook(Book book) {

    }

    /**
     * Удалить сведения о книге из библиотеки
     *
     * @param book
     */
    @Override
    public void deleteBook(Book book) {

    }

    /**
     * Получить сведения о книге книгу по ее id
     *
     * @param id
     */
    @Override
    public Book getBookById(long id) {
        return null;
    }

    /**
     * Получить все книги, имеющиеся в библиотеке
     *
     * @return
     */
    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    /**
     * Получить число всех книг, имеющихся в библиотеке
     */
    @Override
    public long getCountOfBooks() {
        return 0;
    }
}
