package ru.otus.spring14books.services;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.otus.spring14books.nosql.domain.Author;
import ru.otus.spring14books.nosql.domain.Genre;
import ru.otus.spring14books.nosql.services.AuthorService;
import ru.otus.spring14books.nosql.services.GenreService;
import ru.otus.spring14books.sql.domain.Book;

/**
 * Класс BookProcessorImpl формирует новую сущность для записи в MongoDB
 */
@Service
public class BookProcessorImpl implements BookProcessor {

    private final AuthorService authorService;

    private final GenreService genreService;

    public BookProcessorImpl(AuthorService authorService, GenreService genreService) {
        this.authorService = authorService;
        this.genreService = genreService;
    }

    /**
     * Метод process получает сущность Book из реляционной СУБД SQL и возвращает
     * сущность для записи в СУБД NoSQL
     *
     * @param bookSql
     * @return
     */
    @Override
    public ru.otus.spring14books.nosql.domain.Book process(Book bookSql) {
        ru.otus.spring14books.nosql.domain.Book bookNoSql = new ru.otus.spring14books.nosql.domain.Book(
                String.valueOf(new ObjectId()),
                bookSql.getTitle(),
                authorService.getFirstAuthorByFullName(bookSql.getAuthor().getFullName()),
                 genreService.getFirstGenreByName(bookSql.getGenre().getName())
        );
        return bookNoSql;
    }
}
