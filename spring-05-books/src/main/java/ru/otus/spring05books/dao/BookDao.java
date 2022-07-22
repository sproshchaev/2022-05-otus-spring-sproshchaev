package ru.otus.spring05books.dao;

import ru.otus.spring05books.domain.Book;

import java.util.List;

/**
 *
 */
public interface BookDao {

    /**
     * Создать новую книгу
     * @param book
     * @return
     */
    long createBook(Book book);

    /**
     * Обновить сведения о книге
     * @param id
     * @param book
     * @return
     */
    boolean updateBookById(long id, Book book);

    /**
     * Удалить сведения о книге из библиотеки
     * @param id
     * @return
     */
    boolean deleteBookById(long id);

    /**
     * Получить сведения о книге по ее id
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
