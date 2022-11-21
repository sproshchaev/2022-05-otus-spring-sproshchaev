package ru.otus.spring06books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06books.repositories.AuthorRepositoryJpa;
import ru.otus.spring06books.repositories.BookRepositoryJpa;
import ru.otus.spring06books.repositories.CommentRepositoryJpa;
import ru.otus.spring06books.repositories.GenreRepositoryJpa;

/**
 * Класс LibraryServiceImpl содержит методы сервиса работы с библиотекой
 */
@Service
public class LibraryServiceImpl implements LibraryService {
    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;
    private final CommentRepositoryJpa commentRepositoryJpa;
    private final BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    public LibraryServiceImpl(AuthorRepositoryJpa authorRepositoryJpa, GenreRepositoryJpa genreRepositoryJpa,
                              CommentRepositoryJpa commentRepositoryJpa, BookRepositoryJpa bookRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
        this.commentRepositoryJpa = commentRepositoryJpa;
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    @Override
    @Transactional
    public String aboutLibrary() {
        long countOfBooks = bookRepositoryJpa.getCountOfBooks();
        long countOfAuthors = authorRepositoryJpa.getCountOfAuthors();
        long countOfGenres = genreRepositoryJpa.getCountOfGenres();
        long countOfComment = commentRepositoryJpa.getCountOfComment();
        return "Welcome to our library! We have more than " + countOfBooks
                + " books by " + countOfAuthors + " authors and " + countOfGenres + " genres in our library. " +
                "Our readers have left more than " + countOfComment + " comments on the books!";
    }

}
