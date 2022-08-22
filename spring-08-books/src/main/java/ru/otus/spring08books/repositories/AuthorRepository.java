package ru.otus.spring08books.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring08books.entities.Author;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    List<Author> findAllByFullName(String fullName);

    @Override
    Optional<Author> findById(String id);

    @Override
    void delete(Author author);
}