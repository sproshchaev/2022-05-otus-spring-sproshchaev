package ru.otus.spring14books.nosql.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring14books.nosql.domain.Book;
import ru.otus.spring14books.nosql.domain.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepositoryDest extends MongoRepository<Comment, String> {

    List<Comment> findAllByBook(Book book);

    List<Comment> findAll();

    List<Comment> findCommentByCommentTextAndBook(String commentText, Book book);

    @Override
    Optional<Comment> findById(String id);

    @Override
    void delete(Comment comment);

    void deleteAllByBook(Book book);
}
