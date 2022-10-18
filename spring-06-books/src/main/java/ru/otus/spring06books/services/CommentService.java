package ru.otus.spring06books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06books.entities.Book;
import ru.otus.spring06books.entities.Comment;
import ru.otus.spring06books.repositories.CommentRepositoryJpa;

import java.util.List;

/**
 * Класс CommentService содержит методы сервиса работы с репозиторием Комментариев к книгам библиотеки
 */
@Service
public class CommentService {
    private final CommentRepositoryJpa commentRepositoryJpa;

    @Autowired
    public CommentService(CommentRepositoryJpa commentRepositoryJpa) {
        this.commentRepositoryJpa = commentRepositoryJpa;
    }

    /**
     * Метод createComment создает новый комментарий (Crud)
     * Аннотация @Transactional - метод изменяет данные
     *
     * @param idBook
     * @param comment
     * @return
     */
    @Transactional
    public String createComment(long idBook, String comment) {
        Comment newComment = new Comment(new Book(idBook), comment);
        long id = commentRepositoryJpa.createComment(newComment);
        return id != 0 ? "New comment (" + id + ") '" + comment + "' for book id="
                + idBook + " has been successfully created!" : "Error: Something went wrong!";
    }

    /**
     * Метод deleteCommentById удаляет комментарий по id (cruD)
     * Аннотация @Transactional - метод изменяет данные
     *
     * @param id
     * @return
     */
    @Transactional
    public String deleteCommentById(long id) {
        return commentRepositoryJpa.deleteCommentById(id) ? "The comment id=" + id + " has been deleted" : "Error: Something went " +
                "wrong!";
    }

    /**
     * Метод getCommentById возвращает комментарий к книге по его id (cRud)
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public String getCommentById(long id) {
        return commentRepositoryJpa.getCommentById(id).getCommentText();
    }

    /**
     * Метод updateCommentById обновляет комментарий к книге по его id (crUd)
     * Аннотация @Transactional - метод изменяет данные
     *
     * @param id
     * @param comment
     * @return
     */
    @Transactional
    public String updateCommentById(long id, String comment) {
        return commentRepositoryJpa.updateComment(new Comment(id, comment, new Book())) ? "The comment id="
                + id + " has " + "been updated  )" : "Error: Something went wrong!";
    }

    /**
     * Метод getIdByComment возвращает id для комментария
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Метод не подразумевает изменения данных в БД, используется рекомендуемая аннотация @Transactional(readOnly = true)
     *
     * @param comment
     * @return
     */
    @Transactional(readOnly = true)
    public String getIdByComment(Comment comment) {
        long id = commentRepositoryJpa.getIdByComment(comment);
        return id == 0 ? "Comment '" + comment.getCommentText() + "' not found in the library!" : "Comment '"
                + comment.getCommentText() + "' has an id=" + id;
    }

    /**
     * Метод getAllCommentsBookById возвращает все комментарии к книге
     * Если id книги не найден - метод вернет строку ""
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Метод не подразумевает изменения данных в БД, используется рекомендуемая аннотация @Transactional(readOnly = true)
     *
     * @param idBook
     * @return
     */
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
