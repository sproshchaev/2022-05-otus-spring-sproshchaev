package ru.otus.spring17books.restrepositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.spring17books.domain.Comment;

import java.util.List;

/**
 * Интерфейс CommentRepo реализует репозиторий и обработку endpoint для Comment
 * (работает без контроллера, Spring Data REST)
 *
 * @see ru.otus.spring17books.domain.Comment
 */
@RepositoryRestResource(path = "comment")
public interface CommentRepo extends PagingAndSortingRepository<Comment, Long> {

    List<Comment> findAll();

    @RestResource(path = "id", rel = "id")
    List<Comment> findById(long id);

}
