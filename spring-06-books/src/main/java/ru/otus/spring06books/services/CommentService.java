package ru.otus.spring06books.services;

import ru.otus.spring06books.entities.Comment;

public interface CommentService {
    String createComment(long idBook, String comment);

    String deleteCommentById(long id);

    String getCommentById(long id);

    String updateCommentById(long id, String comment);

    String getIdByComment(Comment comment);

    String getAllCommentsBookById(long idBook);
}
