package ru.otus.spring09books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Класс LibraryServiceImpl содержит методы для работы с сервисами авторов, жанров, книг, комментариев
 */
@Service
public class LibraryServiceImpl {
    private final AuthorServiceImpl authorService;
    private final GenreServiceImpl genreService;
    private final CommentServiceImpl commentService;
    private final BookServiceImpl bookService;

    @Autowired
    public LibraryServiceImpl(AuthorServiceImpl authorService, GenreServiceImpl genreService, CommentServiceImpl commentService, BookServiceImpl bookService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
        this.bookService = bookService;
    }

    /**
     * Метод aboutLibrary выводит пользователю строку с приглашением и числом книг в библиотеке
     * Метод не изменяет данные
     *
     * @return
     */
    @Transactional(readOnly = true)
    public String aboutLibrary() {
        long countOfBooks = bookService.countBooks();
        long countOfAuthors = authorService.countAuthors();
        long countOfGenres = genreService.countGenres();
        long countOfComment = commentService.countComments();
        return "Welcome to our library! We have more than " + countOfBooks
                + " books by " + countOfAuthors + " authors and " + countOfGenres + " genres in our library. " +
                "Our readers have left more than " + countOfComment + " comments on the books!";
    }

}