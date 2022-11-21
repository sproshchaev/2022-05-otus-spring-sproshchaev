package ru.otus.spring06books.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring06books.entities.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Класс AuthorRepositoryJpa реализует CRUD операции для класса Author
 *
 * @see ru.otus.spring06books.entities.Author
 */
@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public AuthorRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Author createAuthor(Author author) {
        long authorId = getIdByAuthor(author);
        if (authorId == 0) {
            entityManager.persist(author);
        }
        return author;
    }

    @Override
    public Author getAuthorById(long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public long getIdByAuthor(Author author) {
        TypedQuery<Long> query = entityManager.createQuery("select a.id from Author a where a.fullName = :fullname",
                Long.class);
        query.setParameter("fullname", author.getFullName());
        List<Long> idList = query.getResultList();
        return idList.size() == 0 ? 0 : idList.get(0);
    }

    @Override
    public boolean updateAuthor(Author author) {
        Query query = entityManager.createQuery("update Author a " +
                "set a.fullName = :fullname " +
                "where a.id = :id");
        query.setParameter("fullname", author.getFullName());
        query.setParameter("id", author.getId());
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteAuthor(Author author) {
        Query query = entityManager.createQuery("delete " +
                "from Author a " +
                "where a.id = :id");
        query.setParameter("id", author.getId());
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Author> getAllAuthors() {
        TypedQuery<Author> query = entityManager.createQuery("select a " +
                "from Author a ", Author.class);
        List<Author> authorList = query.getResultList();
        return authorList;
    }

    @Override
    public int getCountOfAuthors() {
        Long result = entityManager.createQuery("select count(a) from Author a", Long.class).getSingleResult();
        return Math.toIntExact(result);
    }

}
