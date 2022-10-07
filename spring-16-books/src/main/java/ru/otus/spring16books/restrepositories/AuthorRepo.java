package ru.otus.spring16books.restrepositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.spring16books.domain.Author;

import java.util.List;

/**
 * Интерфейс AuthorRepo реализует репозиторий и обработку endpoint для Author
 * (работает без контроллера, Spring Data REST)
 *
 * @see ru.otus.spring16books.domain.Author
 */
@RepositoryRestResource(path = "author")
public interface AuthorRepo extends PagingAndSortingRepository<Author, Long> {

    List<Author> findAll();

    @RestResource(path = "fullname", rel = "fullname")
    List<Author> findByFullName(String fullName);

}
