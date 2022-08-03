package ru.otus.spring06books.repositories;

import ru.otus.spring06books.entities.Author;

import java.util.List;

/**
 * Интерфейс AuthorRepository
 */
public interface AuthorRepository {

    /**
     * Метод createAuthor
     *
     * @param author
     * @return
     */
    Author createAuthor(Author author);

    /**
     * Метод getAuthorById
     *
     * @param id
     * @return
     */
    Author getAuthorById(long id);

    /**
     * Метод getIdByAuthor получает id автора
     *
     * @param author
     * @return
     */
    long getIdByAuthor(Author author);

    /**
     * Метод updateAuthor обновляет сведения об авторе в библиотеке
     *
     * @param author
     * @return
     */
    boolean updateAuthor(Author author);

    /**
     * Метод deleteAuthor удаляет сведения об авторе из библиотеки
     *
     * @param author
     * @return
     */
    boolean deleteAuthor(Author author);

    /**
     * Метод getAllAuthors получает сведения по всем авторам из библиотеки
     *
     * @return
     */
    List<Author> getAllAuthors();

    /**
     * Метод getCountOfAuthors получает число авторов, чьи сведения есть в библиотеке
     *
     * @return
     */
    int getCountOfAuthors();

}
