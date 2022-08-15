package ru.otus.spring08books.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.otus.spring08books.entities.Author;

import java.util.List;

@Repository
public interface AuthorRepositoryMongoDb extends MongoRepository<Author, String> {

    List<Author> findAllByFullName(String fullName);

}
