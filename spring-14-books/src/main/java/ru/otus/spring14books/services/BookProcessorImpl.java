package ru.otus.spring14books.services;

import org.bson.types.ObjectId;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Service;
import ru.otus.spring14books.nosql.domain.Author;
import ru.otus.spring14books.nosql.domain.Genre;
import ru.otus.spring14books.nosql.services.AuthorService;
import ru.otus.spring14books.nosql.services.GenreService;
import ru.otus.spring14books.sql.domain.Book;

import java.util.HashMap;
import java.util.List;

/**
 * Класс BookProcessorImpl формирует новую сущность для записи в MongoDB
 */
@Service
public class BookProcessorImpl implements BookProcessor {

    private final AuthorService authorService;

    private final GenreService genreService;

    private HashMap<String, String> author;
    private HashMap<String, String> genre;

    public BookProcessorImpl(AuthorService authorService, GenreService genreService) {
        this.authorService = authorService;
        this.genreService = genreService;
    }

    /**
     * Метод getAllAuthorAndGenre() выполняется единожды при запуске миграции и заполняет кэш с авторами и жанрами
     */
    @BeforeStep
    public void getAllAuthorAndGenre() {
        author = new HashMap<>();
        List<Author> authorList = authorService.getAuthorsList();
        for (int i = 0; i < authorList.size(); i++) {
            author.put(authorList.get(i).getFullName(), authorList.get(i).getId());
        }
        genre = new HashMap<>();
        List<Genre> genreList = genreService.getAllGenresList();
        for (int i = 0; i < genreList.size(); i++) {
            genre.put(genreList.get(i).getName(), genreList.get(i).getId());
        }

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
                new Author(author.get(bookSql.getAuthor().getFullName()), bookSql.getAuthor().getFullName()),
                new Genre(genre.get(bookSql.getGenre().getName()), bookSql.getGenre().getName())
        );
        return bookNoSql;
    }
}
