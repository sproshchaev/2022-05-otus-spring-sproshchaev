package ru.otus.spring05books.dao;

import ru.otus.spring05books.domain.Book;

import java.util.List;

/**
 * Интерфейс BookDao
 */
public interface BookDao {

    /**
     * Метод createBook создает новую книгу в библиотеке
     *
     * @param book
     * @return
     */
    long createBook(Book book);

    /**
     * Метод updateBookById обновляет сведения о книге в библиотеке
     *
     * @param id
     * @param book
     * @return
     */
    boolean updateBookById(long id, Book book);

    /**
     * Метод deleteBookById удаляет сведения о книге из библиотеки
     *
     * @param id
     * @return
     */
    boolean deleteBookById(long id);

    /**
     * Метод getBookById возвращает сведения о книге по ее id
     *
     * @param id
     * @return
     */
    Book getBookById(long id);

    /**
     * Метод getIdByBook возвращает id переданной ему книги
     * @param book
     * @return
     */
    long getIdByBook(Book book);

    /**
     * Метод getAllBooks возвращает коллекцию из всех книг, имеющиеся в библиотеке
     *
     * @return
     */
    List<Book> getAllBooks();

    /**
     * Метод getCountOfBooks возвращает число всех книг, имеющихся в библиотеке
     *
     * @return
     */
    int getCountOfBooks();

}
