package ru.otus.spring06books.repositories;

import ru.otus.spring06books.entities.Author;

import java.util.List;

/**
 * Интерфейс AuthorRepository содержит методы для сущности Author
 *
 * @see ru.otus.spring06books.entities.Author
 */
public interface AuthorRepository {
    Author createAuthor(Author author);

    Author getAuthorById(long id);

    long getIdByAuthor(Author author);

    boolean updateAuthor(Author author);

    boolean deleteAuthor(Author author);

    List<Author> getAllAuthors();

    int getCountOfAuthors();

}
