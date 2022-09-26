package ru.otus.spring14books.sql.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring14books.sql.domain.Comment;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс CommentRepositorySource содержит методы работы со справочником комментариев
 *
 * @see ru.otus.spring14books.sql.domain.Comment
 */
@Repository
public interface CommentRepositorySource extends JpaRepository<Comment, Long> {

    List<Comment> getAllByBookId(long idBook);

    @EntityGraph(attributePaths = {"book"})
    Optional<Comment> findCommentById(long id);

    @Modifying
    @Query("update Comment c set c.commentText = :commentText where c.id = :idComment")
    Integer updateComment(@Param("idComment") long idComment, @Param("commentText") String commentText);

}
