package ru.otus.spring11books.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring11books.domain.Genre;

@Repository
public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

    Flux<Genre> findAllGenreByName(String name);

    @Override
    Mono<Genre> findById(String id);

    @Override
    Mono<Void> delete(Genre genre);
}
