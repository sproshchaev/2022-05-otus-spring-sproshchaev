package ru.otus.spring06books.repositories;

import ru.otus.spring06books.entities.Comment;

import java.util.List;

/**
 * Интерфейс CommentRepository содержит методы для сущности Comment
 *
 * @see ru.otus.spring06books.entities.Comment
 */
public interface CommentRepository {

    /**
     * Метод createComment добавляет новый комментарий к книге
     *
     * @param comment
     * @return
     */
    long createComment(Comment comment);

    /**
     * Метод getCommentById возвращает сущность Comment по его id
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
     * Метод deleteCommentById удаляет комментарий к книге
     *
     * @param id
     * @return
     */
    boolean deleteCommentById(long id);

    /**
     * Метод getAllCommentsBookById получает все комментарии к книге из библиотеки
     *
     * @param idBook
     * @return
     */
    List<Comment> getAllCommentsBookById(long idBook);

    /**
     * Метод getCountOfComment получает число комментариев к книгам в библиотеке
     *
     * @return
     */
    int getCountOfComment();

}
