package ru.otus.spring06books.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring06books.entities.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Класс GenreRepositoryJpa реализует CRUD операции для класса Genre
 *
 * @see ru.otus.spring06books.entities.Genre
 */
@Repository
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public GenreRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long createGenre(Genre genre) {
        long genreId = getIdByGenre(genre);
        if (genreId == 0) {
            entityManager.persist(genre);
            return genre.getId();
        } else {
            return getIdByGenre(genre);
        }
    }

    @Override
    public boolean updateGenre(Genre genre) {
        Query query = entityManager.createQuery("update Genre g " +
                "set g.name = :name " +
                "where g.id = :id");
        query.setParameter("name", genre.getName());
        query.setParameter("id", genre.getId());
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteGenre(Genre genre) {
        Query query = entityManager.createQuery("delete " +
                "from Genre g " +
                "where g.id = :id");
        query.setParameter("id", genre.getId());
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public Genre getGenreById(long id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public long getIdByGenre(Genre genre) {
        TypedQuery<Long> query = entityManager.createQuery("select g.id " +
                        "from Genre g " +
                        "where g.name = :name",
                Long.class);
        query.setParameter("name", genre.getName());
        List<Long> idList = query.getResultList();
        return idList.size() == 0 ? 0 : idList.get(0);
    }

    @Override
    public List<Genre> getAllGenres() {
        TypedQuery<Genre> query = entityManager.createQuery("select g " +
                "from Genre g ", Genre.class);
        List<Genre> genreList = query.getResultList();
        return genreList;
    }

    @Override
    public int getCountOfGenres() {
        Long result = entityManager.createQuery("select count(g) from Genre g", Long.class).getSingleResult();
        return Math.toIntExact(result);
    }

}
