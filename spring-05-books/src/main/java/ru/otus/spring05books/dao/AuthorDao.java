package ru.otus.spring05books.dao;

import ru.otus.spring05books.domain.Author;

import java.util.List;

/**
 * Интерфейс DAO для класса Author
 */
public interface AuthorDao {

    /**
     * Создать автора в библиотеке
     *
     * @param author
     * @return
     */
    long createAuthor(Author author);

    /**
     * Обновить сведения об авторе в библиотеке
     */
    void updateAuthor(Author author);

    /**
     * Удалить сведения об авторе из библиотеки
     */
    void deleteAuthor(Author author);

    /**
     * Получить сведения об авторе по Id
     */
    String getAuthorById();

    /**
     * Получить сведения по всем авторам из библиотеки
     *
     * @return
     */
    List<Author> getAllAuthors();

    /**
     * Получить число авторов, чьи сведения есть в библиотеке
     */
    long getCountOfAuthors();


}
