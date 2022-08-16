package ru.otus.spring08books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс LibraryServiceMongoDb содержит методы для работы библиотекой (как совокупный объект)
 */
@Service
public class LibraryServiceMongoDb implements LibraryService {
    private final AuthorServiceMongoDb authorServiceMongoDb;
    private final GenreServiceMongoDb genreServiceMongoDb;
    private final BookServiceMongoDb bookServiceMongoDb;
    private final CommentServiceMongoDb commentServiceMongoDb;

    @Autowired
    public LibraryServiceMongoDb(AuthorServiceMongoDb authorServiceMongoDb, GenreServiceMongoDb genreServiceMongoDb, BookServiceMongoDb bookServiceMongoDb, CommentServiceMongoDb commentServiceMongoDb) {
        this.authorServiceMongoDb = authorServiceMongoDb;
        this.genreServiceMongoDb = genreServiceMongoDb;
        this.bookServiceMongoDb = bookServiceMongoDb;
        this.commentServiceMongoDb = commentServiceMongoDb;
    }

    /**
     * Метод aboutLibrary выводит пользователю строку с приглашением и числом книг в библиотеке
     * Метод не изменяет данные
     *
     * @return
     */
    @Override
    public String aboutLibrary() {
        long countOfBooks = bookServiceMongoDb.countBooks();
        long countOfAuthors = authorServiceMongoDb.countAuthors();
        long countOfGenres = genreServiceMongoDb.countGenres();
        long countOfComment = commentServiceMongoDb.countComments();
        return "Welcome to our library! \nWe have more than " + countOfBooks
                + " books by " + countOfAuthors + " authors and " + countOfGenres + " genres in our library. \n" +
                "Our readers have left more than " + countOfComment + " comments on the books!";    }
}
