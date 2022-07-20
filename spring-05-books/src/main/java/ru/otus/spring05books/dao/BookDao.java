package ru.otus.spring05books.dao;

import ru.otus.spring05books.domain.Book;

/**
 *
 */
public interface BookDao {

    /**
     * Создать книгу
     */
    void createBook(Book book);

    /**
     * Обновить книгу
     */
    void updateBook();

    /**
     * Удалить книгу
     */
    void deleteBook();

    /**
     * Получить книгу по Id
     */
    String getBookById();

    /**
     * Получить все книги
     *
     * @return
     */
    String getAllBooks();

    /**
     * Получить число книг
     */
    Long getCountOfBook();

}
