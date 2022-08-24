package ru.otus.spring09books.services;

/**
 * Интерфейс BookService содержит методы для сущности Book
 *
 * @see ru.otus.spring09books.domain.Book
 */
public interface BookService {

    /**
     * Число книг в библиотеке
     * @return
     */
    long countBooks();
}