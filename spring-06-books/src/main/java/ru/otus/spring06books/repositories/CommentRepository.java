package ru.otus.spring06books.repositories;

import ru.otus.spring06books.models.Book;
import ru.otus.spring06books.models.Comment;

import java.util.List;

/**
 * Интерфейс CommentRepository
 */
public interface CommentRepository {

    /**
     * Метод createComment
     *
     * @param commentText
     * @param book
     * @return
     */
    Comment createComment(String commentText, Book book);

    /**
     * Метод getCommentById возвращает текст комментария по его id
     *
     * @param id
     * @return
     */
    Comment getCommentById(long id);

    /**
     * Метод getIdByComment возвращает id для комментария
     *
     * @param comment
     * @return
     */
    long getIdByComment(Comment comment);

    /**
     * Метод updateComment обновляет комментарий к книге
     *
     * @param comment
     * @return
     */
    boolean updateComment(Comment comment);

    /**
     * Метод deleteComment удаляет комментарий к книге
     *
     * @param comment
     * @return
     */
    boolean deleteComment(Comment comment);

    /**
     * Метод getAllComment получает все комментарии к книгам из библиотеки
     *
     * @return
     */
    List<Comment> getAllComment();

    /**
     * Метод getCountOfComment получает число комментариев к книгам в библиотеке
     *
     * @return
     */
    int getCountOfComment();

}
