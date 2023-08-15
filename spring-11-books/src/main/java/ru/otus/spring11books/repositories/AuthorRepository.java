package ru.otus.spring11books.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring11books.domain.Author;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    Flux<Author> findAllByFullName(String fullName);

    Mono<Author> findById(String id);

    @Override
    Mono<Void> delete(Author author);

}
