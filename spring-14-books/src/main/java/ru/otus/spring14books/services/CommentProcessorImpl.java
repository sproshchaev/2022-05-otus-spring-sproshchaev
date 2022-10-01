package ru.otus.spring14books.services;

import org.bson.types.ObjectId;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring14books.nosql.domain.Author;
import ru.otus.spring14books.nosql.domain.Genre;
import ru.otus.spring14books.nosql.services.AuthorService;
import ru.otus.spring14books.nosql.services.BookService;
import ru.otus.spring14books.nosql.services.GenreService;
import ru.otus.spring14books.sql.domain.Book;
import ru.otus.spring14books.sql.domain.Comment;

import java.util.HashMap;
import java.util.List;

/**
 * Класс CommentProcessorImpl формирует новую сущность для записи в MongoDB
 */
@Service
public class CommentProcessorImpl implements CommentProcessor {

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookServiceNoSql;

    private final ru.otus.spring14books.sql.services.BookService bookServiceSql;

    private HashMap<String, String> authorHashMap;
    private HashMap<String, String> genreHashMap;
    private HashMap<Long, String> bookIdHashMap;

    @Autowired
    public CommentProcessorImpl(AuthorService authorService, GenreService genreService, BookService bookServiceNoSql,
                                ru.otus.spring14books.sql.services.BookService bookServiceSql) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookServiceNoSql = bookServiceNoSql;
        this.bookServiceSql = bookServiceSql;
    }

    /**
     * Метод getAllAuthorAndGenreAndBookId() выполняется единожды при запуске миграции и заполняет кэш с авторами и жанрами
     */
    @BeforeStep
    public void getAllAuthorAndGenreAndBookId() {
        authorHashMap = new HashMap<>();
        List<Author> authorList = authorService.getAuthorsList();
        for (int i = 0; i < authorList.size(); i++) {
            authorHashMap.put(authorList.get(i).getFullName(), authorList.get(i).getId());
        }
        genreHashMap = new HashMap<>();
        List<Genre> genreList = genreService.getAllGenresList();
        for (int i = 0; i < genreList.size(); i++) {
            genreHashMap.put(genreList.get(i).getName(), genreList.get(i).getId());
        }
        bookIdHashMap = new HashMap<>();
        List<Book> bookListSql = bookServiceSql.getAllBook();
        for (int i = 0; i < bookListSql.size(); i++) {
            ru.otus.spring14books.nosql.domain.Book bookNoSql =
                    bookServiceNoSql.findAllByTitleAndAuthorFullNameAndGenreName(bookListSql.get(i).getTitle(),
                            bookListSql.get(i).getAuthor().getFullName(),
                            bookListSql.get(i).getGenre().getName()
                    ).get(0);
            bookIdHashMap.put(bookListSql.get(i).getId(), bookNoSql.getId());
        }
    }

    /**
     * Метод process получает сущность Comment из реляционной СУБД SQL и возвращает
     * сущность для записи в СУБД NoSQL
     *
     * @param commentSql
     * @return
     */
    @Override
    public ru.otus.spring14books.nosql.domain.Comment process(Comment commentSql) {
        Author author = new Author(authorHashMap.get(commentSql.getBook().getAuthor().getFullName()),
                commentSql.getBook().getAuthor().getFullName());
        Genre genre = new Genre(genreHashMap.get(commentSql.getBook().getGenre().getName()),
                commentSql.getBook().getGenre().getName());
        ru.otus.spring14books.nosql.domain.Book bookNoSql = new ru.otus.spring14books.nosql.domain.Book(
                bookIdHashMap.get(commentSql.getBook().getId()), commentSql.getBook().getTitle(), author, genre);
        ru.otus.spring14books.nosql.domain.Comment commentNoSql = new ru.otus.spring14books.nosql.domain.Comment(
                String.valueOf(new ObjectId()),
                commentSql.getCommentText(),
                bookNoSql);
        return commentNoSql;
    }
}
