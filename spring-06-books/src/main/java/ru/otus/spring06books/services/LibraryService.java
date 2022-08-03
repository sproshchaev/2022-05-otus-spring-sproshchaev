package ru.otus.spring06books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06books.repositories.AuthorRepositoryJpa;
import ru.otus.spring06books.repositories.BookRepositoryJpa;
import ru.otus.spring06books.repositories.CommentRepositoryJpa;
import ru.otus.spring06books.repositories.GenreRepositoryJpa;

/**
 * Класс LibraryService содержит методы сервиса работы с библиотекой
 */
@Service
public class LibraryService {

    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;
    private final CommentRepositoryJpa commentRepositoryJpa;
    private final BookRepositoryJpa bookRepositoryJpa;

    /**
     * Конструктор класса
     *
     * @param authorRepositoryJpa
     * @param genreRepositoryJpa
     * @param commentRepositoryJpa
     * @param bookRepositoryJpa
     */
    @Autowired
    public LibraryService(AuthorRepositoryJpa authorRepositoryJpa, GenreRepositoryJpa genreRepositoryJpa, CommentRepositoryJpa commentRepositoryJpa, BookRepositoryJpa bookRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
        this.commentRepositoryJpa = commentRepositoryJpa;
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    /**
     * Метод aboutLibrary выводит пользователю строку с приглашением и числом книг в библиотеке
     * Аннотация @Transactional - метод изменяет данные
     *
     * @return
     */
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
