package ru.otus.spring12books.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring12books.domain.Author;
import ru.otus.spring12books.domain.Book;
import ru.otus.spring12books.domain.Genre;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс BookRepository содержит методы работы с книгами
 *
 * @see ru.otus.spring12books.domain.Book
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAll();

    @EntityGraph(attributePaths = {"author", "genre"})
    Optional<Book> findBookById(long id);

    List<Book> findBookByTitleAndAuthorAndGenre(String title, Author author, Genre genre);

    @Query("select b.id from Book b " +
            "join Author a on (b.author.fullName = a.fullName) " +
            "join Genre g on (b.genre.name = g.name) " +
            "where (b.title = :title) and (a.fullName = :fullName) and (g.name = :name)")
    List<Long> getIdByBook (@Param("title") String title,
                            @Param("fullName") String fullName,
                            @Param("name") String name);

    @Modifying
    @Query("update Book b set b.title = :title, b.author = :author, b.genre = :genre where b.id = :id")
    Integer updateBook(@Param("id") long id,
                       @Param("title") String title,
                       @Param("author") Author author,
                       @Param("genre") Genre genre);

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findBookByAuthor(Author author);

}