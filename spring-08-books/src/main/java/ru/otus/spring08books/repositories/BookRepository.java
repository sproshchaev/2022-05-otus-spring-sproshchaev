package ru.otus.spring08books.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring08books.entities.Author;
import ru.otus.spring08books.entities.Book;
import ru.otus.spring08books.entities.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByTitleAndAuthorAndGenre(String title, Author author, Genre genre);

    @Override
    Optional<Book> findById(String id);

    @Override
    void delete(Book book);

    List<Book> findBookByAuthor(Author author);

    List<Book> findBookByGenre(Genre genre);
}