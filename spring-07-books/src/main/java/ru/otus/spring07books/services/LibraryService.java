package ru.otus.spring07books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring07books.repositories.AuthorRepository;
import ru.otus.spring07books.repositories.BookRepository;
import ru.otus.spring07books.repositories.CommentRepository;
import ru.otus.spring07books.repositories.GenreRepository;

/**
 * Класс LibraryService содержит методы для работы библиотекой (совокупный объект)
 */
@Service
public class LibraryService {
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LibraryService(AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * Метод aboutLibrary выводит пользователю строку с приглашением и числом книг в библиотеке
     * Метод не изменяет данные
     *
     * @return
     */
    @Transactional(readOnly = true)
    public String aboutLibrary() {
        long countOfBooks = bookRepository.count();
        long countOfAuthors = authorRepository.count();
        long countOfGenres = genreRepository.count();
        long countOfComment = commentRepository.count();
        return "Welcome to our library! We have more than " + countOfBooks
                + " books by " + countOfAuthors + " authors and " + countOfGenres + " genres in our library. " +
                "Our readers have left more than " + countOfComment + " comments on the books!";
    }

}
