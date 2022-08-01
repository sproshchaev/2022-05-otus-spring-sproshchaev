package ru.otus.spring06books.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring06books.entities.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Класс CommentRepositoryJpa
 */
@Repository
public class CommentRepositoryJpa implements CommentRepository {

    /**
     * Внедрение зависимости EntityManager (отвечает за все сущности)
     */
    @PersistenceContext
    private final EntityManager entityManager;

    /**
     * Конструктор класса
     *
     * @param entityManager
     */
    public CommentRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Метод createComment
     * <p>
     * Метод persist кладет сущность в БД, при этом эта сущность должна быть без id
     * В аннотации поля сущности book необходимо указать @ManyToOne(cascade = CascadeType.ALL)
     *
     * @param comment
     * @return
     */
    @Override
    public long createComment(Comment comment) {
        // todo: Найти книгу по id и вставлять комментарий если есть книга
        // Добавить комментарий
//        long Id = getIdByAuthor(author);

        if (comment.getId() <= 0) {
            entityManager.persist(comment);
            return comment.getId();
        } else {
            return entityManager.merge(comment).getId();
        }

    }

    /**
     * Метод getCommentById возвращает текст комментария по его id
     *
     * @param id
     * @return
     */
    @Override
    public Comment getCommentById(long id) {
        return null;
    }

    /**
     * Метод getIdByComment возвращает id для комментария
     *
     * @param comment
     * @return
     */
    @Override
    public long getIdByComment(Comment comment) {
        return 0;
    }

    /**
     * Метод updateComment обновляет комментарий к книге
     *
     * @param comment
     * @return
     */
    @Override
    public boolean updateComment(Comment comment) {
        return false;
    }

    /**
     * Метод deleteComment удаляет комментарий к книге
     *
     * @param comment
     * @return
     */
    @Override
    public boolean deleteComment(Comment comment) {
        return false;
    }

    /**
     * Метод getAllComment получает все комментарии к книгам из библиотеки
     *
     * @return
     */
    @Override
    public List<Comment> getAllComment() {
        return null;
    }

    /**
     * Метод getCountOfComment получает число комментариев к книгам в библиотеке
     *
     * @return
     */
    @Override
    public int getCountOfComment() {
        return 0;
    }
}
