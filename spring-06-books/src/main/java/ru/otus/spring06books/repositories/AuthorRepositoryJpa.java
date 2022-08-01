package ru.otus.spring06books.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring06books.entities.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Класс AuthorRepositoryJpa
 */
@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

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
    public AuthorRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Метод createAuthor
     * <p>
     * Метод persist кладет сущность в БД, при этом эта сущность должна быть без id
     *
     * @param author
     * @return
     */
    @Override
    public Author createAuthor(Author author) {
        long authorId = getIdByAuthor(author);
        if (authorId == 0) {
            entityManager.persist(author);
            return author;
        } else {
            return getAuthorById(authorId);
        }
    }

    /**
     * Метод getAuthorById
     *
     * @param id
     * @return
     */
    @Override
    public Author getAuthorById(long id) {
        return entityManager.find(Author.class, id);
    }

    /**
     * Метод getIdByAuthor получает id автора
     *
     * @param author
     * @return
     */
    @Override
    public long getIdByAuthor(Author author) {
        TypedQuery<Author> query = entityManager.createQuery("select a " +
                        "from Author a " +
                        "where a.fullName = :fullname",
                Author.class);
        query.setParameter("fullname", author.getFullName());
        List<Author> authorList = query.getResultList();
        return authorList.size() == 0 ? 0 : authorList.get(0).getId();
    }

    /**
     * Метод updateAuthor обновляет сведения об авторе в библиотеке
     *
     * @param author
     * @return
     */
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

    /**
     * Метод deleteAuthor удаляет сведения об авторе из библиотеки
     *
     * @param author
     * @return
     */
    @Override
    public boolean deleteAuthor(Author author) {
        Query query = entityManager.createQuery("delete " +
                "from Author a " +
                "where a.id = :id");
        query.setParameter("id", author.getId());
        int result = query.executeUpdate();
        return result == 1;
    }

    /**
     * Метод getAllAuthors получает сведения по всем авторам из библиотеки
     *
     * @return
     */
    @Override
    public List<Author> getAllAuthors() {
        TypedQuery<Author> query = entityManager.createQuery("select a " +
                "from Author a ", Author.class);
        List<Author> authorList = query.getResultList();
        return authorList;
    }

    /**
     * Метод getCountOfAuthors получает число авторов, чьи сведения есть в библиотеке
     *
     * @return
     */
    @Override
    public int getCountOfAuthors() {
        Long result = entityManager.createQuery("select count(a) from Author a", Long.class).getSingleResult();
        return Math.toIntExact(result);
    }


}
