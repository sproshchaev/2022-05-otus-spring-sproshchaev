package ru.otus.spring06books.repositories;

import ru.otus.spring06books.entities.Comment;

import java.util.List;

/**
 * Интерфейс CommentRepository
 */
public interface CommentRepository {

    /**
     * Метод createComment
     *
     * @param book
     * @param commentText
     * @return
     */
    long createComment(Comment comment);

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
