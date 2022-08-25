package ru.otus.spring09books.services;

import ru.otus.spring09books.domain.Book;

import java.util.List;

/**
 * Интерфейс BookService содержит методы для работы с сущностью Book
 *
 * @see ru.otus.spring09books.domain.Book
 */
public interface BookService {

    /**
     * Метод getAllBook возвращает все книги из библиотеки (cRud)
     *
     * @return
     */
    List<Book> getAllBook();

    /**
     * Метод возвращает все книги автора
     *
     * @param authorFullName
     * @return
     */
    List<Book> getAllBookByAuthor(String authorFullName);

    /**
     * Число книг в библиотеке
     *
     * @return
     */
    long countBooks();

}