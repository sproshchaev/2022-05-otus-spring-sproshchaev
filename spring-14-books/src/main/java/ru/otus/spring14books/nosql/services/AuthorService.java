package ru.otus.spring14books.nosql.services;

import ru.otus.spring14books.nosql.domain.Author;
import ru.otus.spring14books.nosql.repositories.GenreRepositoryDest;

/**
 * Интерфейс AuthorService содержит набор методов для работы с репозиторием сущности Author
 *
 * @see ru.otus.spring14books.nosql.domain.Author
 * @see GenreRepositoryDest
 */
public interface AuthorService {

    /**
     * Метод createNewAuthor создает нового автора в библиотеке (Crud)
     * Метод изменяет данные
     *
     * @param fullName
     * @return
     */
    String createNewAuthor(String fullName);

    /**
     * Метод getIdByAuthor возвращает информацию в текстовом виде о результатах поиска
     * автора с данным id в библиотеке (cRud)
     * Метод не изменяет данные
     *
     * @param fullName
     * @return
     */
    String getIdByAuthor(String fullName);

    /**
     * Метод getAuthorById получает данные об авторе по его id (cRud)
     * Метод не изменяет данные
     *
     * @param id
     * @return
     */
    String getAuthorById(String id);

    /**
     * Метод getAllAuthors получает список всех авторов из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    String getAllAuthors();

    /**
     * Метод updateAuthor обновляет данные об авторе в библиотеке (crUd)
     * Метод изменяет данные
     *
     * @param id
     * @param fullName
     * @return
     */
    String updateAuthor(String id, String fullName);

    /**
     * Метод deleteAuthorById удаляет данные об авторе в библиотеке (cruD)
     * Метод изменяет данные
     *
     * @param id
     * @return
     */
    String deleteAuthorById(String id);

    /**
     * Метод getFirstAuthorByFullName возвращает первого автора из списка
     * авторов с одинаковым значением поля fullName (cRud)
     * Метод не изменяет данные
     *
     * @param authorFullName
     * @return
     */
    Author getFirstAuthorByFullName(String authorFullName);

    /**
     * Метод countAuthors возвращает число авторов
     * @return
     */
    Long countAuthors();
}
