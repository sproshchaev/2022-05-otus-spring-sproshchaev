package ru.otus.spring06books.repositories;

import ru.otus.spring06books.models.Author;

/**
 * Интерфейс AuthorRepository
 */
public interface AuthorRepository {

    /**
     * Метод createAuthor
     * @param author
     * @return
     */
    Author createAuthor(Author author);

    /**
     * Метод getAuthorById
     * @param id
     * @return
     */
    Author getAuthorById(long id);

    /**
     * Метод getIdByAuthor получает id автора
     * @param author
     * @return
     */
    long getIdByAuthor(Author author);

    /**
     * Метод updateAuthor обновляет сведения об авторе в библиотеке
     * @param author
     * @return
     */
    boolean updateAuthor(Author author);

}
