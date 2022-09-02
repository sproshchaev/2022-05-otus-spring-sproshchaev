package ru.otus.spring10books.services;

import ru.otus.spring10books.domain.Author;

import java.util.List;

/**
 * Интерфейс AuthorService содержит методы для работы с сущностью Author
 *
 * @see ru.otus.spring10books.domain.Author
 */
public interface AuthorService {

    /**
     * Метод getFirstAuthorByFullName возвращает первого автора из списка, авторов с одинаковым значением поля fullName
     * Метод не изменяет данные
     *
     * @param authorFullName
     * @return
     */
    Author getFirstAuthorByFullName(String authorFullName);

    /**
     * Метод getAllAuthors получает список всех авторов из библиотеки (cRud)
     *
     * @return
     */
    List<Author> getAllAuthors();

    /**
     * Число авторов в библиотеке
     *
     * @return
     */
    long countAuthors();

}