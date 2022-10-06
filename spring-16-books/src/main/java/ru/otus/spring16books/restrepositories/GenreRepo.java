package ru.otus.spring16books.restrepositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.spring16books.domain.Genre;

import java.util.List;

@RepositoryRestResource(path = "genre")
public interface GenreRepo extends PagingAndSortingRepository<Genre, Long> {

    List<Genre> findAll();

    @RestResource(path = "name", rel = "name")
    List<Genre> findByName(String name);

}
