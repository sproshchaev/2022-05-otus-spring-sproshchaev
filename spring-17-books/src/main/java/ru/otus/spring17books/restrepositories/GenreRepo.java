package ru.otus.spring17books.restrepositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.spring17books.domain.Genre;

import java.util.List;

/**
 * Интерфейс GenreRepo реализует репозиторий и обработку endpoint для Genre
 * (работает без контроллера, Spring Data REST)
 *
 * @see ru.otus.spring17books.domain.Genre
 */
@RepositoryRestResource(path = "genre")
public interface GenreRepo extends PagingAndSortingRepository<Genre, Long> {

    List<Genre> findAll();

    @RestResource(path = "name", rel = "name")
    List<Genre> findByName(String name);

}
