package ru.otus.spring06books.repositories;

import org.springframework.stereotype.Repository;
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

    @PersistenceContext
    private final EntityManager entityManager;
    private final BookRepositoryJpa bookRepositoryJpa;

    public CommentRepositoryJpa(EntityManager entityManager, BookRepositoryJpa bookRepositoryJpa) {
        this.entityManager = entityManager;
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    @Override
    public long createComment(Comment comment) {
        if (bookRepositoryJpa.getBookById(comment.getBook().getId()) != null) {
            if (comment.getId() <= 0) {
                entityManager.persist(comment);
                return comment.getId();
            } else {
                return entityManager.merge(comment).getId();
            }
        } else return 0;
    }

    @Override
    public Comment getCommentById(long id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    public long getIdByComment(Comment comment) {
        TypedQuery<Long> query = entityManager.createQuery("select c.id from Comment c " +
                "where c.commentText = :commentText", Long.class);
        query.setParameter("commentText", comment.getCommentText());
        List<Long> idList = query.getResultList();
        return idList.size() == 0 ? 0 : idList.get(0);
    }

    @Override
    public boolean updateComment(Comment comment) {
        Query query = entityManager.createQuery("update Comment c " + "set c.commentText = :commentText " +
                "where c.id = :id");
        query.setParameter("id", comment.getId());
        query.setParameter("commentText", comment.getCommentText());
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteCommentById(long id) {
        Query query = entityManager.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Comment> getAllCommentsBookById(long idBook) {
        TypedQuery<Comment> query = entityManager.createQuery("select c from Comment c where c.book.id = :idBook",
                Comment.class);
        query.setParameter("idBook", idBook);
        List<Comment> commentsList = query.getResultList();
        return commentsList;
    }

    @Override
    public int getCountOfComment() {
        Long result = entityManager.createQuery("select count(с) from Comment с", Long.class).getSingleResult();
        return Math.toIntExact(result);
    }

}
