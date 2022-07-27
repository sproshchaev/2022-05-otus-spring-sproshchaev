package ru.otus.spring05books.dao;

import ru.otus.spring05books.domain.Author;

import java.util.List;

/**
 * Интерфейс DAO для класса Author
 */
public interface AuthorDao {

    /**
     * Метод createAuthor создает нового автора в библиотеке
     *
     * @param author
     * @return
     */
    long createAuthor(Author author);

    /**
     * Метод updateAuthor обновляет сведения об авторе в библиотеке
     * @param author
     * @return
     */
    boolean updateAuthor(Author author);

    /**
     * Метод deleteAuthor удаляет сведения об авторе из библиотеки
     * @param author
     * @return
     */
    boolean deleteAuthor(Author author);

    /**
     * Метод getAuthorById получает сведения об авторе по Id
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
     * Метод getAllAuthors получает сведения по всем авторам из библиотеки
     *
     * @return
     */
    List<Author> getAllAuthors();

    /**
     * Метод getCountOfAuthors получает число авторов, чьи сведения есть в библиотеке
     * @return
     */
    int getCountOfAuthors();

}
