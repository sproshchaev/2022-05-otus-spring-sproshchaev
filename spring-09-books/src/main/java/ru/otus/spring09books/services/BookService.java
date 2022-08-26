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
     * Метод updateBookById обновляет данные по книге: название, автора, жанр (crUd)
     *
     * @param id
     * @param title
     * @param authorFullName
     * @param genreName
     * @return
     */
    int updateBookById(long id, String title, String authorFullName, String genreName);

    /**
     * Метод createBook (Crud)
     *
     * @param title          (book.title)
     * @param authorFullName (author.fullName)
     * @param genreName      (genre.name)
     * @return
     */
    Book createNewBook(String title, String authorFullName, String genreName);

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