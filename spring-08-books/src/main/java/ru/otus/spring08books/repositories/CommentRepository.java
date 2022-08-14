package ru.otus.spring08books.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring08books.entities.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

}
