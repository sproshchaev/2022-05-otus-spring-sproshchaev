package ru.otus.spring05books.shell;

import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring05books.dao.*;
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
    private final AuthorDaoJdbc authorDaoJdbc; // убрать
    private final AuthorDaoJdbc2 authorDaoJdbc2; // заменить

    private final BookDaoJdbc bookDaoJdbc;  // убрать
    private final BookDaoJdbc2 bookDaoJdbc2; // заменить

    /**
     * Конструктор класса AppEventsCommands
     * Примечание: классы GenreDaoJdbc, AuthorDaoJdbc, BookDaoJdbc для возможности применения @Autowired отмечены аннотацией
     *
     * @param genreDaoJdbc
     * @param authorDaoJdbc
     * @param authorDaoJdbc2
     * @param bookDaoJdbc
     * @param bookDaoJdbc2
     * @Repository
     */
    @Autowired
    public AppEventsCommands(GenreDaoJdbc genreDaoJdbc, AuthorDaoJdbc authorDaoJdbc, AuthorDaoJdbc2 authorDaoJdbc2, BookDaoJdbc bookDaoJdbc, BookDaoJdbc2 bookDaoJdbc2) {
        this.genreDaoJdbc = genreDaoJdbc;
        this.authorDaoJdbc = authorDaoJdbc;
        this.authorDaoJdbc2 = authorDaoJdbc2;
        this.bookDaoJdbc = bookDaoJdbc; // убрать
        this.bookDaoJdbc2 = bookDaoJdbc2; // заменить
    }

    /**
     * Метод aboutLibrary выводит пользователю строку с приглашением и числом книг в библиотеке
     * Сокращенный вызов: "a", "about"
     *
     * @return
     */
    @ShellMethod(value = "Information about the library", key = {"a", "about"})
    public String aboutLibrary() {
        long countOfBooks = bookDaoJdbc2.getCountOfBooks();
        long countOfAuthors = authorDaoJdbc.getCountOfAuthors();
        long countOfGenres = genreDaoJdbc.getCountOfGenres();
        return "Welcome to our library! We have more than " + countOfBooks
                + " books by " + countOfAuthors + " authors and " + countOfGenres + " genres in our library";
    }

    /**
     * Метод createNewGenre - создает наименование нового жанра книг в библиотеке
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
     * Метод getIdByGenre возвращает id для жанра, если он есть в библиотеке
     * Сокращенный вызов: "gibg", "getidbygenre" --name genre_name
     * Пример: gibg --name 'History'
     */
    @ShellMethod(value = "Getting a genre id", key = {"gibg", "getidbygenre"})
    public String getIdByGenre(@ShellOption(defaultValue = "History") String name) {
        long id = genreDaoJdbc.getIdByGenre(new Genre(name));
        return id == 0 ? "Genre '" + name + "' not found in the library!" : "Genre '" + name + "' has an id=" + id;
    }

    /**
     * Метод createNewAuthor
     * Сокращенный вызов: "ca", "createauthor" --name author_fullname
     * Пример: ca --fullName 'Stephen Edwin King'
     *
     * @param fullName
     * @return
     */
    @ShellMethod(value = "Create a new Author of books in the library", key = {"ca", "createauthor"})
    public String createNewAuthor(@ShellOption(defaultValue = "Stephen Edwin King") String fullName) {
        long id = authorDaoJdbc2.createAuthor(new Author(fullName));
        return id != 0 ? "New author (" + id + ") " + fullName + " has been successfully created!" : "Error: Something went wrong!";
    }

    /**
     * Метод getIdByAuthor возвращает id для полного имени данного автора, если он есть в библиотеке
     * Сокращенный вызов: "giba", "getidbyauthor" --fullName author_fullname
     * Пример: giba --fullName 'Daniel Defoe'
     */
    @ShellMethod(value = "Getting an id by author", key = {"giba", "getidbyauthor"})
    public String getIdByAuthor(@ShellOption(defaultValue = "Daniel Defoe") String fullName) {
        long id = authorDaoJdbc2.getIdByAuthor(new Author(fullName));
        return id == 0 ? "Author '" + fullName + "' not found in the library!" : "Author '" + fullName + "' has an id=" + id;
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
        long id = bookDaoJdbc2.createBook(new Book(title, new Author(author), new Genre(genre)));
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
        return bookDaoJdbc2.getBookById(id).toString();
    }

    /**
     * Метод updateBookById (crUd) обновляет данные по книге: название, автора, жанр
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
        return bookDaoJdbc2.updateBookById(id, new Book(title, new Author(author), new Genre(genre))) ? "The book id="
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
        return bookDaoJdbc2.deleteBookById(id) ? "The book id=" + id + " has been deleted" : "Error: Something went " +
                "wrong!";
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
        return bookDaoJdbc2.getAllBooks().toString();
    }

    /**
     * Метод getIdByBook возвращает id для книги, если она есть в библиотеке.
     * Поиск выполняется только по названию, автору и жанру книги
     * Сокращенный вызов: "gibb", "getidbybook" --title title --fullName fullName --genre name
     * Пример: gibb --title 'The Pilgrim’s Progress' --fullName 'John Bunyan' --name 'History'
     */
    @ShellMethod(value = "Getting an id by book", key = {"gibb", "getidbybook"})
    public String getIdByBook(@ShellOption(defaultValue = "The Pilgrim’s Progress") String title,
                              @ShellOption(defaultValue = "John Bunyan") String fullName,
                              @ShellOption(defaultValue = "History") String name) {
        String fullNameBook = title + " " + fullName + " (" + name + ")";
        long id = bookDaoJdbc2.getIdByBook(new Book(title, new Author(fullName), new Genre(name)));
        return id == 0 ? "Book '" + fullNameBook + "' not found in the library!" : "Book '" + fullNameBook + "' has an id=" + id;
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
