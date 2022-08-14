package ru.otus.spring08books.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring08books.entities.Genre;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {

    Genre findGenreByName(String name);

}
