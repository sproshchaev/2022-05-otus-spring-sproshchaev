package ru.otus.spring07books.shell;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring07books.services.AuthorService;
import ru.otus.spring07books.services.BookService;
import ru.otus.spring07books.services.GenreService;

import java.sql.SQLException;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;

    public AppEventsCommands(AuthorService authorService, GenreService genreService, BookService bookService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookService = bookService;
    }

    /**
     * Метод createNewAuthor (Crud)
     * Сокращенный вызов: "ca", "createauthor" --name author_fullname
     * Пример: ca --fullName 'Stephen Edwin King'
     *
     * @param fullName
     * @return
     */
    @ShellMethod(value = "Create a new Author of books in the library", key = {"ca", "createauthor"})
    public String createNewAuthor(@ShellOption(defaultValue = "Stephen Edwin King") String fullName) {
        return authorService.createNewAuthor(fullName);
    }

    /**
     * Метод getIdByAuthor возвращает id для полного имени данного автора, если он есть в библиотеке
     * Сокращенный вызов: "giba", "getidbyauthor" --fullName author_fullname
     * Пример: giba --fullName 'Daniel Defoe'
     *
     * @param fullName
     * @return
     */
    @ShellMethod(value = "Getting an id by author", key = {"giba", "getidbyauthor"})
    public String getIdByAuthor(@ShellOption(defaultValue = "Daniel Defoe") String fullName) {
        return authorService.getIdByAuthor(fullName);
    }

    /**
     * Метод getAuthorById получает данные об авторе по его id (cRud)
     * Сокращенный вызов: "gabi", "getauthorbyid" --id id
     * Пример: gabi --id 2
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Getting information about the author from the library by id", key = {"gabi", "getAuthorById"})
    public String getAuthorById(@ShellOption(defaultValue = "2") long id) {
        return authorService.getAuthorById(id);
    }

    /**
     * Метод getAllAuthors получает список всех авторов из библиотеки (cRud)
     * Сокращенный вызов: "gaa", "getallauthors"
     * Пример: getallauthors
     *
     * @return
     */
    @ShellMethod(value = "Getting a list of all authors from the library", key = {"gaa", "getallauthors"})
    public String getAllAuthors() {
        return authorService.getAllAuthors();
    }

    /**
     * Метод updateAuthor обновляет данные об авторе в библиотеке (crUd)
     * Сокращенный вызов: "ua", "updateauthor" --id id --fullName full_name
     * Пример: ua --id 1 --fullName 'Gianni Rodari'
     *
     * @param id
     * @param fullName
     * @return
     */
    @ShellMethod(value = "Updating information about the author", key = {"ua", "updateauthor"})
    public String updateAuthor(
            @ShellOption(defaultValue = "1") long id,
            @ShellOption(defaultValue = "Gianni Rodari") String fullName) {
        return authorService.updateAuthor(id, fullName);
    }

    /**
     * Метод deleteAuthorById удаляет данные об авторе в библиотеке (cruD)
     * Сокращенный вызов: "dabi", "deleteauthorbyid" --id id
     * Пример: dabi --id 3
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Deleting author by id from the library", key = {"dabi", "deleteauthorbyid"})
    public String deleteAuthorById(
            @ShellOption(defaultValue = "3") long id) {
        return authorService.deleteAuthorById(id);
    }

    /**
     * Метод createGenre - создает наименование нового жанра книг в библиотеке
     * Сокращенный вызов: "cg", "creategenre" --name genre_name
     * Пример: cg --name Novel
     *
     * @param name
     * @return
     */
    @ShellMethod(value = "Create a new genre of books in the library", key = {"cg", "creategenre"})
    public String createGenre(@ShellOption(defaultValue = "Novel") String name) {
        return genreService.createGenre(name);
    }

    /**
     * Метод getIdByGenre возвращает id для жанра, если он есть в библиотеке
     * Сокращенный вызов: "gibg", "getidbygenre" --name genre_name
     * Пример: gibg --name 'History'
     *
     * @param name
     * @return
     */
    @ShellMethod(value = "Getting a genre id", key = {"gibg", "getidbygenre"})
    public String getIdByGenre(@ShellOption(defaultValue = "History") String name) {
        return genreService.getIdByGenre(name);
    }

    /**
     * Метод getGenreById получает данные о жанре по его id
     * Сокращенный вызов: "ggbi", "getgenrebyid" --id id
     * Пример: ggbi --id 2
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Getting information about the author from the library by id", key = {"ggbi", "getgenrebyid"})
    public String getGenreById(
            @ShellOption(defaultValue = "2") long id) {
        return genreService.getGenreById(id);
    }

    /**
     * Метод getAllGenres получает список всех жанров из библиотеки (cRud)
     * Сокращенный вызов: "gag", "getallgenres"
     * Пример: getallgenres
     *
     * @return
     */
    @ShellMethod(value = "Getting a list of all genres from the library", key = {"gag", "getallgenres"})
    public String getAllGenres() {
        return genreService.getAllGenres();
    }

    /**
     * Метод updateGenre обновляет данные о жанре в библиотеке
     * Сокращенный вызов: "ug", "updategenre" --id id --name name
     * Пример: ug --id 1 --name 'Politics'
     *
     * @param id
     * @param name
     * @return
     */
    @ShellMethod(value = "Updating information about the genre", key = {"ug", "updategenre"})
    public String updateGenre(
            @ShellOption(defaultValue = "1") long id,
            @ShellOption(defaultValue = "Politics") String name) {
        return genreService.updateGenre(id, name);
    }

    /**
     * Метод deleteGenreById удаляет данные о жанре в библиотеке
     * Сокращенный вызов: "dgbi", "deletegenrebyid" --id id
     * Пример: dgbi --id 5
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Deleting genre data from the library", key = {"dgbi", "deletegenrebyid"})
    public String deleteGenreById(@ShellOption(defaultValue = "5") long id) {
        return genreService.deleteGenreById(id);
    }

    /**
     * Метод createNewBook (Crud)
     * Сокращенный вызов: "cb", "createbook" --title title_book --author author --genre genre
     * Пример: cb --title 'A Life in Letters' --author 'Arthur Conan Doyle' --genre Autobiography
     *
     * @param title
     * @param author
     * @param genre
     * @return
     */
    @ShellMethod(value = "Add information about a new book, author, genre to the library", key = {"cb", "createbook"})
    public String createNewBook(@ShellOption(defaultValue = "A Life in Letters") String title,
                                @ShellOption(defaultValue = "Arthur Conan Doyle") String author,
                                @ShellOption(defaultValue = "Autobiography") String genre) {
        return bookService.createNewBook(title, author, genre);
    }

    /**
     * Метод getIdByBook возвращает id для книги, если она есть в библиотеке (cRud)
     * Поиск выполняется по названию, автору и жанру книги
     * Сокращенный вызов: "gibb", "getidbybook" --title title --fullName fullName --genre name
     * Пример: gibb --title 'The Pilgrim’s Progress' --fullName 'John Bunyan' --name 'History'
     */
    @ShellMethod(value = "Getting an id by book", key = {"gibb", "getidbybook"})
    public String getIdByBook(@ShellOption(defaultValue = "The Pilgrim’s Progress") String title,
                              @ShellOption(defaultValue = "John Bunyan") String fullName,
                              @ShellOption(defaultValue = "History") String name) {
        return bookService.getIdByBook(title, fullName, name);
    }

    /**
     * Метод getBookById возвращает книгу по ее id (cRud)
     * Сокращенный вызов: "gbbi", "getbookbyid" --id id
     * Пример: gbbi --id 1
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Get book data by its id", key = {"gbbi", "getbookbyid"})
    public String getBookById(@ShellOption(defaultValue = "1") long id) {
        return bookService.getBookById(id);
    }

    // ---

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