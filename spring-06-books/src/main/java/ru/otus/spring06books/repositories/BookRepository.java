package ru.otus.spring06books.repositories;

import ru.otus.spring06books.entities.Book;

import java.util.List;

/**
 * Интерфейс BookRepository содержит методы для сущности Book
 *
 * @see ru.otus.spring06books.entities.Book
 */
public interface BookRepository {

    long createBook(Book book);

    boolean updateBookById(long id, Book book);

    boolean deleteBookById(long id);

    Book getBookById(long id);

    long getIdByBook(Book book);

    List<Book> getAllBooks();

    int getCountOfBooks();
}
