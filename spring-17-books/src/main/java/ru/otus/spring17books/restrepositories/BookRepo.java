package ru.otus.spring17books.restrepositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.spring17books.domain.Book;

import java.util.List;

/**
 * Интерфейс AuthorRepo реализует репозиторий и обработку endpoint для Book
 * (работает без контроллера, Spring Data REST)
 *
 * @see ru.otus.spring17books.domain.Book
 */
@RepositoryRestResource(path = "book")
public interface BookRepo extends PagingAndSortingRepository<Book, Long> {

    List<Book> findAll();

    @RestResource(path = "title", rel = "title")
    List<Book> findByTitle(String title);
}
