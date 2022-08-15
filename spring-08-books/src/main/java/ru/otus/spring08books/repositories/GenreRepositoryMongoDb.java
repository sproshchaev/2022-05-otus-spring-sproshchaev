package ru.otus.spring08books.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring08books.entities.Genre;

import java.util.List;

@Repository
public interface GenreRepositoryMongoDb extends MongoRepository<Genre, String> {
    List<Genre> findAllGenreByName(String name);

}
