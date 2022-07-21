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
        long countOfAuthors = 0;
        long countOfGenres = 0;
        return "Welcome to our library! We have more than " + countOfBooks
                + " books by " + countOfAuthors + " authors and " + countOfGenres + " genres in our library";
    }

    /**
     * Метод createNewGenre - создает новый наименование нового жанра книг в библиотеке
     * Сокращенный вызов: "cg", "creategenre" --name genre_name
     * Пример: cg --name Novel
     */
    @ShellMethod(value = "Create a new genre of books in the library", key = {"cg", "creategenre"})
    public String createNewGenre(@ShellOption(defaultValue = "New_Genre") String name) {
        long id = genreDaoJdbc.createGenre(new Genre(name));
        return "New genre (" + id + ") " + name + " has been successfully created!";
    }

    /**
     * Метод createNewAuthor
     * Сокращенный вызов: "cg", "creategenre" --name author_fullname
     * Пример: cg --name 'Arthur Conan Doyle'
     */
    @ShellMethod(value = "Create a new Author of books in the library", key = {"ca", "createauthor"})
    public String createNewAuthor(@ShellOption(defaultValue = "New_Author") String fullName) {
        long id = authorDaoJdbc.createAuthor(new Author(fullName));
        return "New author (" + id + ") " + fullName + " has been successfully created!";
    }

    /**
     * Метод createNewBook
     * Сокращенный вызов: "cb", "createbook" --title title_book --author author --genre genre
     * Пример: cb --title 'A Life in Letters' --author 'Arthur Conan Doyle' --genre autobiography
     */
    @ShellMethod(value = "Add information about a new book, author, genre to the library", key = {"cb", "createbook"})
    public String createNewBook(@ShellOption(defaultValue = "New_book") String title,
                                @ShellOption(defaultValue = "New_Author") String author,
                                @ShellOption(defaultValue = "New_Genre") String genre) {
        long id = bookDaoJdbc.createBook(new Book(title, new Author(author), new Genre(genre)));
        return "The book (" + id + ") " + title + ", " + author + " (" + genre + ") has been successfully entered!";
    }


    //

    /**
     * Метод ...
     * Сокращенный вызов: "cg", "creategenre" --name author_fullname
     * Пример: cg --name 'Arthur Conan Doyle'
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

}
