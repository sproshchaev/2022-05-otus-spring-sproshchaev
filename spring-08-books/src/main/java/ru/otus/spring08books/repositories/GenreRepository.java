package ru.otus.spring08books.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring08books.entities.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {
    List<Genre> findAllGenreByName(String name);

    @Override
    Optional<Genre> findById(String id);

    @Override
    void delete(Genre genre);
}