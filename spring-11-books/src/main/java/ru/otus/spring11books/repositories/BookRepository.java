package ru.otus.spring11books.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring11books.domain.Author;
import ru.otus.spring11books.domain.Book;
import ru.otus.spring11books.domain.Genre;

import java.util.List;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    List<Book> findAllByTitleAndAuthorAndGenre(String title, Author author, Genre genre);

    Mono<Book> findById(String id);

    @Override
    Mono<Void> delete(Book book);

    Flux<Book> findBookByAuthor(Author author);

    Flux<Book> findBookByGenre(Genre genre);

    Flux<Book> findAll();
}
