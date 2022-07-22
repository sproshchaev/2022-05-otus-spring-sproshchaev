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
     *
     * @param name
     * @return
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
     *
     * @param fullName
     * @return
     */
    @ShellMethod(value = "Create a new Author of books in the library", key = {"ca", "createauthor"})
    public String createNewAuthor(@ShellOption(defaultValue = "New_Author") String fullName) {
        long id = authorDaoJdbc.createAuthor(new Author(fullName));
        return "New author (" + id + ") " + fullName + " has been successfully created!";
    }

    /**
     * Метод createNewBook (Crud)
     * Сокращенный вызов: "cb", "createbook" --title title_book --author author --genre genre
     * Пример: cb --title 'A Life in Letters' --author 'Arthur Conan Doyle' --genre autobiography
     *
     * @param title
     * @param author
     * @param genre
     * @return
     */
    @ShellMethod(value = "Add information about a new book, author, genre to the library", key = {"cb", "createbook"})
    public String createNewBook(@ShellOption(defaultValue = "Title") String title,
                                @ShellOption(defaultValue = "Author") String author,
                                @ShellOption(defaultValue = "Genre") String genre) {
        long id = bookDaoJdbc.createBook(new Book(title, new Author(author), new Genre(genre)));
        return "The book (" + id + ") " + title + ", " + author + " (" + genre + ") has been successfully entered!";
    }

    /**
     * Метод getBookById (cRud)
     * Сокращенный вызов: "gb", "getbook" --id id
     * Пример: gb --id 1
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Get book data by its id", key = {"gb", "getbook"})
    public String getBookById(@ShellOption(defaultValue = "1") long id) {
        return bookDaoJdbc.getBookById(id).toString();
    }

    /**
     * Метод updateBookById (crUd)
     * Сокращенный вызов: "ub", "updatebook" --id id --title new_title --author new_author --genre new_genre
     * Пример: ub --id 1 --title 'New title' --author 'New Author' --genre 'New Genre'
     *
     * @param id
     * @param title
     * @param author
     * @param genre
     * @return
     */
    @ShellMethod(value = "Update book data by id", key = {"ub", "updatebook"})
    public String updateBookById(@ShellOption(defaultValue = "1") long id,
                                 @ShellOption(defaultValue = "New_Title") String title,
                                 @ShellOption(defaultValue = "New_Author") String author,
                                 @ShellOption(defaultValue = "New_Genre") String genre) {
        return bookDaoJdbc.updateBookById(id, new Book(title, new Author(author), new Genre(genre))) ? "The book id="
                + id + " has " + "been updated!" : "Error: Something went wrong";
    }

    /**
     * Метод deleteBookById (cruD)
     * Сокращенный вызов: "db", "deletebook" --id id
     * Пример: db --id 1
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Deleting the selected book by id", key = {"db", "deletebook"})
    public String deleteBookById(@ShellOption(defaultValue = "1") long id) {
        return bookDaoJdbc.deleteBookById(id) ? "The book id=" + id + " has been deleted" : "Error: Something went " +
                "wrong";
    }

    /**
     * Метод getAllBook
     * Сокращенный вызов: "gab", "getallbook"
     * Пример: gab
     *
     * @return
     */
    @ShellMethod(value = "Get a list of all library books", key = {"gab", "getallbook"})
    public String getAllBook() {
        return bookDaoJdbc.getAllBooks().toString();
    }

    /**
     * Метод startConsoleH2 запускает консоль
     */
    @ShellMethod(value = "Start console H2", key = {"c", "console"})
    public void startConsoleH2() {
        try {
            Console.main();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
