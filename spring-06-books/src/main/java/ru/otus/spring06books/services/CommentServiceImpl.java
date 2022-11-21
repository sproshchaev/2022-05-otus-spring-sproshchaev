package ru.otus.spring06books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06books.entities.Book;
import ru.otus.spring06books.entities.Comment;
import ru.otus.spring06books.repositories.CommentRepositoryJpa;

import java.util.List;

/**
 * Класс CommentServiceImpl содержит методы сервиса работы с репозиторием Комментариев к книгам библиотеки
 */
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepositoryJpa commentRepositoryJpa;

    @Autowired
    public CommentServiceImpl(CommentRepositoryJpa commentRepositoryJpa) {
        this.commentRepositoryJpa = commentRepositoryJpa;
    }

    @Override
    @Transactional
    public String createComment(long idBook, String comment) {
        Comment newComment = new Comment(new Book(idBook), comment);
        long id = commentRepositoryJpa.createComment(newComment);
        return id != 0 ? "New comment (" + id + ") '" + comment + "' for book id="
                + idBook + " has been successfully created!" : "Error: Something went wrong!";
    }

    @Override
    @Transactional
    public String deleteCommentById(long id) {
        return commentRepositoryJpa.deleteCommentById(id) ? "The comment id=" + id + " has been deleted" : "Error: Something went " +
                "wrong!";
    }

    @Override
    @Transactional(readOnly = true)
    public String getCommentById(long id) {
        return commentRepositoryJpa.getCommentById(id).getCommentText();
    }

    @Override
    @Transactional
    public String updateCommentById(long id, String comment) {
        return commentRepositoryJpa.updateComment(new Comment(id, comment, new Book())) ? "The comment id="
                + id + " has " + "been updated  )" : "Error: Something went wrong!";
    }

    @Override
    @Transactional(readOnly = true)
    public String getIdByComment(Comment comment) {
        long id = commentRepositoryJpa.getIdByComment(comment);
        return id == 0 ? "Comment '" + comment.getCommentText() + "' not found in the library!" : "Comment '"
                + comment.getCommentText() + "' has an id=" + id;
    }

    @Override
    @Transactional(readOnly = true)
    public String getAllCommentsBookById(long idBook) {
        List<Comment> commentList = commentRepositoryJpa.getAllCommentsBookById(idBook);
        String commentsString = "All comments on this book: ";
        for (int i = 0; i < commentList.size(); i++) {
            commentsString = commentsString + " " + commentList.get(i).getCommentText() + (i < (commentList.size() - 1)
                    ? ", " : ".");
        }
        return commentList.size() == 0 ? "Genres not found!" : commentsString;
    }

}
