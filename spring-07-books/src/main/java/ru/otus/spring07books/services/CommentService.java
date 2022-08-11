package ru.otus.spring07books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring07books.entities.Book;
import ru.otus.spring07books.entities.Comment;
import ru.otus.spring07books.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

/**
 * Класс CommentService содержит методы для работы с репозиторием комментариев
 *
 * @see ru.otus.spring07books.repositories.CommentRepository
 */
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Метод createCommentByIdBook создает новый комментарий (Crud)
     * Уникальность текста комментария не проверяется.
     * Метод изменяет данные
     *
     * @param idBook
     * @param comment
     * @return
     */
    @Transactional
    public String createCommentByIdBook(long idBook, String comment) {
        Comment newComment = new Comment(comment, new Book(idBook));
        newComment = commentRepository.save(newComment);
        return newComment.getId() != 0 ? "New comment '" + comment + "' for book id=" + idBook
                + " has been successfully created!" : "Error: Something went wrong!";
    }

    /**
     * Метод getCommentById возвращает комментарий к книге по его id (cRud)
     * Метод не изменяет данные
     *
     * @param idComment
     * @return
     */
    @Transactional(readOnly = true)
    public String getCommentById(long idComment) {
        Optional<Comment> comment = commentRepository.findCommentById(idComment);
/*
        Optional<Comment> comment = commentRepository.findById(idComment);
*/
        return comment.isPresent() ? "Comment on the book ('" + comment.get().getBook().getTitle() + "' "
                + comment.get().getBook().getAuthor().getFullName() + " "
                + comment.get().getBook().getGenre().getName() + ") id=" + idComment + ":"
                : "Comment with id=" + idComment + " not found!";
    }

    /**
     * Метод getAllCommentsBookById возвращает все комментарии к книге
     * Если id книги не найден - метод вернет строку ""
     * Метод не изменяет данные
     *
     * @param idBook
     * @return
     */
    @Transactional(readOnly = true)
    public String getAllCommentsBookById(long idBook) {
        List<Comment> commentList = commentRepository.getAllByBookId(idBook);
        String commentsString = "All comments on book id=" + idBook + ": ";
        for (int i = 0; i < commentList.size(); i++) {
            commentsString = commentsString + " " + commentList.get(i).getCommentText() + (i < (commentList.size() - 1)
                    ? ", " : ".");
        }
        return commentList.size() == 0 ? "Genres not found!" : commentsString;
    }

    /**
     * Метод updateCommentById обновляет комментарий к книге по его id (crUd)
     * Метод изменяет данные
     *
     * @param idComment
     * @param commentText
     * @return
     */
    @Transactional
    public String updateCommentById(long idComment, String commentText) {
        return commentRepository.updateComment(idComment, commentText) == 1
                ? "The comment id=" + idComment + " has been updated: '" + commentText + "'"
                : "Error: Something went wrong!";
    }

    /**
     * Метод deleteCommentById удаляет комментарий по id (cruD)
     * Метод изменяет данные
     *
     * @param idComment
     * @return
     */
    @Transactional
    public String deleteCommentById(long idComment) {
        if (commentRepository.findById(idComment).isPresent()) {
            commentRepository.deleteById(idComment);
            return "The comment id=" + idComment + " has been deleted";
        } else {
            return "Comment id=" + idComment + " not found!";
        }
    }

}
