package ru.otus.spring14books.nosql.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring14books.nosql.domain.Author;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс AuthorRepositorySource содержит методы работы со справочником авторов
 *
 * @see ru.otus.spring14books.nosql.domain.Author
 */
@Repository
public interface AuthorRepositoryDest extends MongoRepository<Author, String> {

    List<Author> findAllByFullName(String fullName);

    @Override
    Optional<Author> findById(String id);

    @Override
    void delete(Author author);
}
