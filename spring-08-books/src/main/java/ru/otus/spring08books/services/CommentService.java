package ru.otus.spring08books.services;

/**
 * Интерфейс CommentService содержит набор методов для работы с репозиторием сущности Comment
 *
 * @see ru.otus.spring08books.entities.Comment
 */
interface CommentService {
    /**
     * Метод createCommentByIdBook создает новый комментарий (Crud)
     * Метод изменяет данные
     *
     * @param idBook
     * @param comment
     * @return
     */
    String createCommentByIdBook(String idBook, String comment);

    /**
     * Метод getCommentById возвращает комментарий к книге по его id (cRud)
     * Метод не изменяет данные
     *
     * @param idComment
     * @return
     */
    String getCommentById(String idComment);

    /**
     * Метод getAllCommentsBookById возвращает все комментарии к книге
     * Если id книги не найден - метод вернет строку ""
     * Метод не изменяет данные
     *
     * @param idBook
     * @return
     */
    String getAllCommentsBookById(String idBook);

    /**
     * Метод updateCommentById обновляет комментарий к книге по его id (crUd)
     * Метод изменяет данные
     *
     * @param idComment
     * @param commentText
     * @return
     */
    String updateCommentById(String idComment, String commentText);

    /**
     * Метод deleteCommentById удаляет комментарий по id (cruD)
     * Метод изменяет данные
     *
     * @param idComment
     * @return
     */
    String deleteCommentById(String idComment);

    /**
     * Метод deleteAllCommentBook удаляет все комментарии у книги
     *
     * @param idBook
     * @return
     */
    String deleteAllCommentBook(String idBook);

    /**
     * Метод countComments возвращает число комментариев
     *
     * @return
     */
    Long countComments();
}