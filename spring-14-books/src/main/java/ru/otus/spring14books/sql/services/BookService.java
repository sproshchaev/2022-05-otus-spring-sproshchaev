package ru.otus.spring14books.sql.services;

import ru.otus.spring14books.sql.domain.Book;

import java.util.List;

/**
 * Интерфейс BookService содержит методы для работы с сущностью Book
 *
 * @see ru.otus.spring14books.sql.domain.Book
 */
public interface BookService {

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
     * Метод getBookById возвращает книгу по ее id (cRud)
     *
     * @param id
     * @return
     */
    Book getBookById(long id);

    /**
     * Метод getAllBook возвращает все книги из библиотеки (cRud)
     *
     * @return
     */
    List<Book> getAllBook();

    /**
     * Метод возвращает все книги автора (cRud)
     *
     * @param authorFullName
     * @return
     */
    List<Book> getAllBookByAuthor(String authorFullName);

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
     * Метод deleteBookById (cruD)
     *
     * @param id
     * @return
     */
    String deleteBookById(long id);

    /**
     * Число книг в библиотеке
     *
     * @return
     */
    long countBooks();

}
