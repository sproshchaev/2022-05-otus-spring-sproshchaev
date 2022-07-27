package ru.otus.spring06books.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring06books.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
     * @param entityManager
     */
    public AuthorRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Метод save
     *
     * @param author
     * @return
     */
    @Override
    public Author save(Author author) {
        if (author.getId() == 0) {
            entityManager.persist(author);
        }
        return entityManager.merge(author);
    }

    /**
     * Метод findById
     *
     * @param id
     * @return
     */
    @Override
    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }
}
