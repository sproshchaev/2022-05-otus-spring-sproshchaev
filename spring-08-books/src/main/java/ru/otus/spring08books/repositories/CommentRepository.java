package ru.otus.spring08books.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring08books.entities.Book;
import ru.otus.spring08books.entities.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBook(Book book);

    @Override
    Optional<Comment> findById(String id);

    @Override
    void delete(Comment comment);

    void deleteAllByBook(Book book);
}