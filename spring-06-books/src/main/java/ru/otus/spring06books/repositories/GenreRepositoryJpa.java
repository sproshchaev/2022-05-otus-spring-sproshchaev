package ru.otus.spring06books.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring06books.entities.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Класс GenreRepositoryJpa
 */
@Repository
public class GenreRepositoryJpa implements GenreRepository {

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
    public GenreRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Метод createGenre создает новый жанр в библиотеке
     * <p>
     * Метод persist кладет сущность в БД, при этом эта сущность должна быть без id
     *
     * @param genre
     * @return
     */
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

    /**
     * Метод updateGenre обновляет сведения о жанре в библиотеке
     *
     * @param genre
     * @return
     */
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

    /**
     * Метод deleteGenre удаляет сведения об жанре из библиотеки
     *
     * @param genre
     * @return
     */
    @Override
    public boolean deleteGenre(Genre genre) {
        Query query = entityManager.createQuery("delete " +
                "from Genre g " +
                "where g.id = :id");
        query.setParameter("id", genre.getId());
        int result = query.executeUpdate();
        return result == 1;
    }

    /**
     * Метод getGenreById формирует сведения о жанре по id
     * <p>
     * Метод find осуществляет поиск и загрузку сущности по id
     *
     * @param id
     * @return
     */
    @Override
    public Genre getGenreById(long id) {
        return entityManager.find(Genre.class, id);
    }

    /**
     * Метод getIdByGenre возвращает id передаваемого жанра
     *
     * @param genre
     * @return
     */
    @Override
    public long getIdByGenre(Genre genre) {
        TypedQuery<Genre> query = entityManager.createQuery("select g " +
                        "from Genre g " +
                        "where g.name = :name",
                Genre.class);
        query.setParameter("name", genre.getName());
        List<Genre> genreList = query.getResultList();
        return genreList.size() == 0 ? 0 : genreList.get(0).getId();
    }

    /**
     * Метод getAllGenres формирует сведения по всем жанрам из библиотеки
     *
     * @return
     */
    @Override
    public List<Genre> getAllGenres() {
        TypedQuery<Genre> query = entityManager.createQuery("select g " +
                "from Genre g ", Genre.class);
        List<Genre> genreList = query.getResultList();
        return genreList;
    }

    /**
     * Метод getCountOfGenres возвращает число жанров, которые есть в библиотеке
     *
     * @return
     */
    @Override
    public int getCountOfGenres() {
        Long result = entityManager.createQuery("select count(g) from Genre g", Long.class).getSingleResult();
        return Math.toIntExact(result);
    }
}
