package ru.otus.spring05books.shell;

import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring05books.dao.AuthorDaoJdbc;
import ru.otus.spring05books.dao.BookDaoJdbc;
import ru.otus.spring05books.dao.GenreDaoJdbc;
import ru.otus.spring05books.domain.Author;
import ru.otus.spring05books.domain.Book;
import ru.otus.spring05books.domain.Genre;

import java.sql.SQLException;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {
    private final GenreDaoJdbc genreDaoJdbc;
    private final AuthorDaoJdbc authorDaoJdbc;
    private final BookDaoJdbc bookDaoJdbc;

    /**
     * Конструктор класса AppEventsCommands
     * Примечание: классы GenreDaoJdbc, AuthorDaoJdbc, BookDaoJdbc для возможности применения @Autowired отмечены аннотацией
     *
     * @param genreDaoJdbc
     * @param authorDaoJdbc
     * @param bookDaoJdbc
     * @Repository
     */
    @Autowired
    public AppEventsCommands(GenreDaoJdbc genreDaoJdbc, AuthorDaoJdbc authorDaoJdbc, BookDaoJdbc bookDaoJdbc) {
        this.genreDaoJdbc = genreDaoJdbc;
        this.authorDaoJdbc = authorDaoJdbc;
        this.bookDaoJdbc = bookDaoJdbc;
    }

    /**
     * Метод aboutLibrary выводит пользователю строку с приглашением и числом книг в библиотеке
     * Сокращенный вызов: "a", "about"
     *
     * @return
     */
    @ShellMethod(value = "Information about the library", key = {"a", "about"})
    public String aboutLibrary() {
        long countOfBooks = bookDaoJdbc.getCountOfBooks();
        return "Welcome to the library! We have " + countOfBooks + " books";
    }

    /**
     * Метод createGenre - создает новый наименование нового жанра книг в библиотеке
     * Сокращенный вызов: "cg", "creategenre" --name genre_name
     * Пример: cg --name Novel
     */
    @ShellMethod(value = "Create a new genre of books in the library", key = {"cg", "creategenre"})
    public String createGenre(@ShellOption(defaultValue = "New_Genre") String name) {
        long id = genreDaoJdbc.createGenre(new Genre(name));
        return "New genre (" + id + ") " + name + " has been successfully created!";
    }

    //

    /**
     * Метод ...
     * Сокращенный вызов:
     *
     */

    /**
     * Метод startConsoleH2 запускает консоль
     *
     * @return
     * @throws SQLException
     */
    @ShellMethod(value = "Start console H2", key = {"c", "console"})
    public void startConsoleH2() throws SQLException {
        Console.main();
    }

    /**
     * Метод createBook создает книгу: наименование, автор, жанр
     *
     * @throws SQLException
     */
    @ShellMethod(value = "Create book", key = {"cr", "create"})
    public String createBook(@ShellOption(defaultValue = "New_book") String title,
                             @ShellOption(defaultValue = "1") int author_id,
                             @ShellOption(defaultValue = "1") int genre_id) {

        bookDaoJdbc.createBook(new Book(title, new Author("1"), new Genre("1")));
        return "Book " + title + " " + author_id + " " + genre_id + " added!";
    }


}
