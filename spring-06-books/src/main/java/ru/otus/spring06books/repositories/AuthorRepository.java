package ru.otus.spring06books.repositories;

import ru.otus.spring06books.models.Author;

/**
 * Интерфейс AuthorRepository
 */
public interface AuthorRepository {

    /**
     * Метод save
     * @param author
     * @return
     */
    Author save(Author author);

    /**
     * Метод findById
     * @param id
     * @return
     */
    Author findById(long id);
}
