package ru.otus.spring07books.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring07books.entities.Author;

import java.util.List;

/**
 * Интерфейс AuthorRepository содержит методы работы со справочником авторов
 *
 * @see ru.otus.spring07books.entities.Author
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a.id from Author a where a.fullName = :fullName")
    List<Long> getAuthorIdByFullName(@Param("fullName") String fullName);

    @Query("select a from Author a where a.fullName = :fullName")
    List<Author> getAuthorByFullName(@Param("fullName") String fullName);

    @Modifying
    @Query("update Author a set a.fullName = :fullName where a.id = :id")
    Integer updateAuthor(@Param("id") long id, @Param("fullName") String fullName);

}
