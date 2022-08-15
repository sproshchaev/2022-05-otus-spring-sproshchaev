package ru.otus.spring08books.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring08books.entities.Author;
import ru.otus.spring08books.entities.Book;
import ru.otus.spring08books.entities.Genre;

import java.util.List;

@Repository
public interface BookRepositoryMongoDb extends MongoRepository<Book, String> {

    List<Book> findAllByTitleAndAuthorAndGenre(String title, Author author, Genre genre);

}
