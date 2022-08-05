package ru.otus.spring06books.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring06books.entities.Book;
import ru.otus.spring06books.entities.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Класс CommentRepositoryJpa реализует CRUD операции для класса Comment
 *
 * @see ru.otus.spring06books.entities.Comment
 */
@Repository
public class CommentRepositoryJpa implements CommentRepository {

    /**
     * Внедрение зависимости EntityManager (отвечает за все сущности)
     */
    @PersistenceContext
    private final EntityManager entityManager;
    private final BookRepositoryJpa bookRepositoryJpa;

    /**
     * Конструктор класса
     *
     * @param entityManager
     * @param bookRepositoryJpa
     */
    public CommentRepositoryJpa(EntityManager entityManager, BookRepositoryJpa bookRepositoryJpa) {
        this.entityManager = entityManager;
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    /**
     * Метод createComment создает новый комментарий к книге
     *
     * @param comment
     * @return
     * @see ru.otus.spring06books.entities.Comment#Comment(Book, String)
     * <p>
     * Метод persist кладет сущность в БД, при этом эта сущность должна быть без id
     * В аннотации поля сущности book необходимо указать @ManyToOne(cascade = CascadeType.ALL)
     */
    @Override
    public long createComment(Comment comment) {
        System.out.println(comment.toString());
        if (bookRepositoryJpa.getBookById(comment.getBook().getId()) != null) {
            if (comment.getId() <= 0) {
                entityManager.persist(comment);
                return comment.getId();
            } else {
                return entityManager.merge(comment).getId();
            }
        } else return 0;
    }

    /**
     * Метод getCommentById возвращает текст комментария по его id
     *
     * @param id
     * @return
     */
    @Override
    public Comment getCommentById(long id) {
        return entityManager.find(Comment.class, id);
    }

    /**
     * Метод getIdByComment возвращает id для комментария
     *
     * @param comment
     * @return
     */
    @Override
    public long getIdByComment(Comment comment) {
        TypedQuery<Long> query = entityManager.createQuery("select c.id " +
                        "from Comment c " +
                        "where c.commentText = :commentText",
                Long.class);
        query.setParameter("commentText", comment.getCommentText());
        List<Long> idList = query.getResultList();
        return idList.size() == 0 ? 0 : idList.get(0);
    }

    /**
     * Метод updateComment обновляет комментарий к книге
     *
     * @param comment
     * @return
     */
    @Override
    public boolean updateComment(Comment comment) {
        Query query = entityManager.createQuery("update Comment c " +
                "set c.commentText = :commentText " +
                "where c.id = :id");
        query.setParameter("id", comment.getId());
        query.setParameter("commentText", comment.getCommentText());
        int result = query.executeUpdate();
        return result == 1;
    }

    /**
     * Метод deleteCommentById удаляет комментарий к книге
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteCommentById(long id) {
        Query query = entityManager.createQuery("delete " +
                "from Comment c " +
                "where c.id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    /**
     * Метод getAllComment получает все комментарии к книгам из библиотеки
     *
     * @return
     */
    @Override
    public List<Comment> getAllComment() {
        TypedQuery<Comment> query = entityManager.createQuery("select c " +
                "from Comment c ", Comment.class);
        List<Comment> commentsList = query.getResultList();
        return commentsList;
    }

    /**
     * Метод getCountOfComment получает число комментариев к книгам в библиотеке
     *
     * @return
     */
    @Override
    public int getCountOfComment() {
        Long result = entityManager.createQuery("select count(с) from Comment с", Long.class).getSingleResult();
        return Math.toIntExact(result);
    }
}
