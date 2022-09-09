package ru.otus.spring11books.repositories;

import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring11books.domain.Book;
import ru.otus.spring11books.domain.Comment;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

    Flux<Comment> findCommentsByBook(Book book);

    Mono<Comment> findById(String id);

    @Override
    Mono<Void> delete(Comment comment);

    Mono<Void> deleteAllByBook(Book book);
}
