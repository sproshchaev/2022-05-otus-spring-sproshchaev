package ru.otus.spring14books.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring14books.nosql.domain.Author;
import ru.otus.spring14books.nosql.domain.Genre;
import ru.otus.spring14books.nosql.services.AuthorService;
import ru.otus.spring14books.nosql.services.BookService;
import ru.otus.spring14books.nosql.services.GenreService;
import ru.otus.spring14books.sql.domain.Comment;

/**
 * Класс CommentProcessorImpl формирует новую сущность для записи в MongoDB
 */
@Service
public class CommentProcessorImpl implements CommentProcessor {

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

    @Autowired
    public CommentProcessorImpl(AuthorService authorService, GenreService genreService, BookService bookService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookService = bookService;
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

        System.out.println("Получен commentSql: " + commentSql.getId() + " " + commentSql.getCommentText() + " book " +
                commentSql.getBook().getId() + " " + commentSql.getBook().getTitle() + " "
                        + commentSql.getBook().getAuthor().getId() + " " + commentSql.getBook().getAuthor().getFullName());

        Author author = authorService.getFirstAuthorByFullName(commentSql.getBook().getAuthor().getFullName());
        Genre genre = genreService.getFirstGenreByName(commentSql.getBook().getGenre().getName());

        ru.otus.spring14books.nosql.domain.Comment commentNoSql = new ru.otus.spring14books.nosql.domain.Comment(
                String.valueOf(new ObjectId()),
                commentSql.getCommentText(),
                bookService.findAllByTitleAndAuthorAndGenre(commentSql.getBook().getTitle(), author, genre).get(0)
        );
        System.out.println("Комментарий для MongoDB " + commentNoSql.getId() + " " + commentNoSql.getCommentText()); // todo удалить!
        return commentNoSql;
    }
}
