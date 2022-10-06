package ru.otus.spring16books.restrepositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.spring16books.domain.Comment;

import java.util.List;

@RepositoryRestResource(path = "comment")
public interface CommentRepo extends PagingAndSortingRepository<Comment, Long> {

    List<Comment> findAll();

    @RestResource(path = "id", rel = "id")
    List<Comment> findById(long id);

}
