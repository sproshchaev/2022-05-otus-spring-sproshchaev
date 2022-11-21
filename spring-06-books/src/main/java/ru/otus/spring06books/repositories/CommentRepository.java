package ru.otus.spring06books.repositories;

import ru.otus.spring06books.entities.Comment;

import java.util.List;

/**
 * Интерфейс CommentRepository содержит методы для сущности Comment
 *
 * @see ru.otus.spring06books.entities.Comment
 */
public interface CommentRepository {

    long createComment(Comment comment);

    Comment getCommentById(long id);

    long getIdByComment(Comment comment);

    boolean updateComment(Comment comment);

    boolean deleteCommentById(long id);

    List<Comment> getAllCommentsBookById(long idBook);

    int getCountOfComment();

}
