package ru.otus.spring07books.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring07books.entities.Comment;
import ru.otus.spring07books.entities.Genre;

import java.util.List;

/**
 * Интерфейс CommentRepository содержит методы работы со справочником жанров
 *
 * @see ru.otus.spring07books.entities.Comment
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getAllByBookId(long idBook);

    @Modifying
    @Query("update Comment c set c.commentText = :commentText where c.id = :idComment")
    Integer updateComment(@Param("idComment") long idComment, @Param("commentText") String commentText);

}
