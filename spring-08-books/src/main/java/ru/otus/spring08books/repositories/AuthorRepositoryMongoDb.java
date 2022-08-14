package ru.otus.spring08books.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring08books.entities.Author;

@Repository
public interface AuthorRepositoryMongoDb extends MongoRepository<Author, String> {

    Author findAuthorByFullName(String fullName);

}
