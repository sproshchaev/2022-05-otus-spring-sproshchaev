package ru.otus.spring08books.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring08books.entities.Book;
import ru.otus.spring08books.entities.Comment;

import java.util.List;

@Repository
public interface CommentRepositoryMongoDb extends MongoRepository<Comment, String> {

    List<Comment> findAllByBook(Book book);

}
