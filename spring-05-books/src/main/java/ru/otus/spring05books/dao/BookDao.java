package ru.otus.spring05books.dao;

import ru.otus.spring05books.domain.Book;

import java.util.List;

/**
 *
 */
public interface BookDao {

    /**
     * Создать новую книгу
     */
    void createBook(Book book);

    /**
     * Обновить сведения о книге
     */
    void updateBook(Book book);

    /**
     * Удалить сведения о книге из библиотеки
     */
    void deleteBook(Book book);

    /**
     * Получить сведения о книге книгу по ее id
     */
    Book getBookById(long id);

    /**
     * Получить все книги, имеющиеся в библиотеке
     *
     * @return
     */
    List<Book> getAllBooks();

    /**
     * Получить число всех книг, имеющихся в библиотеке
     */
    long getCountOfBooks();

}
