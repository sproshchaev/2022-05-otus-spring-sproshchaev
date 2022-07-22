package ru.otus.spring05books.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring05books.domain.Author;
import ru.otus.spring05books.domain.Book;
import ru.otus.spring05books.domain.Genre;

import java.util.ArrayList;
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
     * @param id
     * @param book
     * @return
     */
    @Override
    public boolean updateBookById(long id, Book book) {
        return true;
    }

    /**
     * Удалить сведения о книге из библиотеки
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteBookById(long id) {
        return true;
    }

    /**
     * Получить сведения о книге книгу по ее id
     *
     * @param id
     */
    @Override
    public Book getBookById(long id) {
        return new Book("1", new Author("1"), new Genre("1"));
    }

    /**
     * Получить все книги, имеющиеся в библиотеке
     *
     * @return
     */
    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("1", new Author("1"), new Genre("1")));
        return bookList;
    }

    /**
     * Получить число всех книг, имеющихся в библиотеке
     */
    @Override
    public long getCountOfBooks() {
        return 0;
    }
}
