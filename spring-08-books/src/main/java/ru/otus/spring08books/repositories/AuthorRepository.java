package ru.otus.spring08books.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring08books.entities.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    default void w2() {
        System.out.println();
    }

    Author findAuthorByFullName(String fullName);

}
