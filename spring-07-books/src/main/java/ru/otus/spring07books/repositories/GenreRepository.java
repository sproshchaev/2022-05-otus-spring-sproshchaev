package ru.otus.spring07books.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring07books.entities.Genre;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Интерфейс GenreRepository содержит методы работы со справочником жанров
 *
 * @see ru.otus.spring07books.entities.Genre
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("select g.id from Genre g where g.name = :name")
    List<Long> getGenreIdByName(@Param("name") String name);

    @Query("select g from Genre g where g.name = :name")
    List<Genre> getGenreByName(@Param("name") String name);

    @Modifying
    @Query("update Genre g set g.name = :name where g.id = :id")
    Integer updateGenre(@Param("id") long id, @Param("name") String fullName);

}
