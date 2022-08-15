package ru.otus.spring08books.services;

import org.springframework.stereotype.Service;
import ru.otus.spring08books.repositories.CommentRepositoryMongoDb;

/**
 * Класс CommentServiceMongoDb содержит методы для работы с репозиторием комментариев
 *
 * @see CommentRepositoryMongoDb
 */
@Service
public class CommentServiceMongoDb implements CommentService {

    /**
     * Метод createCommentByIdBook создает новый комментарий (Crud)
     * Уникальность текста комментария не проверяется.
     * Метод изменяет данные
     *
     * @param idBook
     * @param comment
     * @return
     */
    @Override
    public String createCommentByIdBook(long idBook, String comment) {
        return null;
    }

    /**
     * Метод getCommentById возвращает комментарий к книге по его id (cRud)
     * Метод не изменяет данные
     *
     * @param idComment
     * @return
     */
    @Override
    public String getCommentById(long idComment) {
        return null;
    }

    /**
     * Метод getAllCommentsBookById возвращает все комментарии к книге
     * Если id книги не найден - метод вернет строку ""
     * Метод не изменяет данные
     *
     * @param idBook
     * @return
     */
    @Override
    public String getAllCommentsBookById(long idBook) {
        return null;
    }

    /**
     * Метод updateCommentById обновляет комментарий к книге по его id (crUd)
     * Метод изменяет данные
     *
     * @param idComment
     * @param commentText
     * @return
     */
    @Override
    public String updateCommentById(long idComment, String commentText) {
        return null;
    }

    /**
     * Метод deleteCommentById удаляет комментарий по id (cruD)
     * Метод изменяет данные
     *
     * @param idComment
     * @return
     */
    @Override
    public String deleteCommentById(long idComment) {
        return null;
    }
}
