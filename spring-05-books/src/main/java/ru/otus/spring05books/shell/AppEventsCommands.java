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
import java.util.List;

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
     * @param genreDaoJdbc
     * @param authorDaoJdbc
     * @param authorDaoJdbc
     * @param bookDaoJdbc
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
        long countOfAuthors = authorDaoJdbc.getCountOfAuthors();
        long countOfGenres = genreDaoJdbc.getCountOfGenres();
        return "Welcome to our library! We have more than " + countOfBooks
                + " books by " + countOfAuthors + " authors and " + countOfGenres + " genres in our library";
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
     * Метод updateGenre обновляет данные о жанре в библиотеке
     * Сокращенный вызов: "ug", "updategenre" --id id --name name
     * Пример: ug --id 1 --name 'Politics'
     */
    @ShellMethod(value = "Updating information about the genre", key = {"ug", "updategenre"})
    public String updateGenre(
            @ShellOption(defaultValue = "1") long id,
            @ShellOption(defaultValue = "Politics") String name) {
        boolean result = genreDaoJdbc.updateGenre(new Genre(id, name));
        return result ? "Information about the genre (" + "id=" + id + " " + name + ") has been updated!"
                : "Error: Something went wrong!";
    }

    /**
     * Метод deleteGenre удаляет данные о жанре в библиотеке
     * Сокращенный вызов: "dg", "deletegenre" --id id --name name
     * Пример: dg --id 5 --name 'Fiction'
     */
    @ShellMethod(value = "Deleting genre data from the library", key = {"dg", "deletegenre"})
    public String deleteGenre(
            @ShellOption(defaultValue = "5") long id,
            @ShellOption(defaultValue = "Fiction") String name) {
        boolean result = genreDaoJdbc.deleteGenre(new Genre(id, name));
        return result ? "Genre (id=" + id + " " + name + ") removed from the library"
                : "Error: Something went wrong!";
    }

    /**
     * Метод getGenreById получает данные о жанре по его id
     * Сокращенный вызов: "ggbi", "getgenrebyid" --id id
     * Пример: ggbi --id 2
     */
    @ShellMethod(value = "Getting information about the author from the library by id", key = {"ggbi", "getgenrebyid"})
    public String getGenreById(
            @ShellOption(defaultValue = "2") long id) {
        Genre result = genreDaoJdbc.getGenreById(id);
        return result == null ? "Genre not found!" : result.toString();
    }

    /**
     * Метод getAllGenres получает список всех жанров из библиотеки (cRud)
     * Сокращенный вызов: "gag", "getallgenres"
     * Пример: getallgenres
     */
    @ShellMethod(value = "Getting a list of all genres from the library", key = {"gag", "getallgenres"})
    public String getAllGenres() {
        List<Genre> result = genreDaoJdbc.getAllGenres();
        return result.size() == 0 ? "Genres not found!" : result.toString();
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
        long id = authorDaoJdbc.createAuthor(new Author(fullName));
        return id != 0 ? "New author (" + id + ") " + fullName + " has been successfully created!" : "Error: Something went wrong!";
    }

    /**
     * Метод getIdByAuthor возвращает id для полного имени данного автора, если он есть в библиотеке
     * Сокращенный вызов: "giba", "getidbyauthor" --fullName author_fullname
     * Пример: giba --fullName 'Daniel Defoe'
     */
    @ShellMethod(value = "Getting an id by author", key = {"giba", "getidbyauthor"})
    public String getIdByAuthor(@ShellOption(defaultValue = "Daniel Defoe") String fullName) {
        long id = authorDaoJdbc.getIdByAuthor(new Author(fullName));
        return id == 0 ? "Author '" + fullName + "' not found in the library!" : "Author '" + fullName + "' has an id=" + id;
    }

    /**
     * Метод updateAuthor обновляет данные об авторе в библиотеке (crUd)
     * Сокращенный вызов: "ua", "updateauthor" --id id --fullName full_name
     * Пример: ua --id 1 --fullName 'Gianni Rodari'
     */
    @ShellMethod(value = "Updating information about the author", key = {"ua", "updateauthor"})
    public String updateAuthor(
            @ShellOption(defaultValue = "1") long id,
            @ShellOption(defaultValue = "Gianni Rodari") String fullName) {
        boolean result = authorDaoJdbc.updateAuthor(new Author(id, fullName));
        return result ? "Information about the author (" + "id=" + id + " " + fullName + ") has been updated!"
                : "Error: Something went wrong!";
    }

    /**
     * Метод deleteAuthor удаляет данные об авторе в библиотеке (cruD)
     * Сокращенный вызов: "da", "deleteauthor" --id id --fullName full_name
     * Пример: da --id 3 --fullName 'Gianni Rodari'
     */
    @ShellMethod(value = "Deleting author data from the library", key = {"da", "deleteauthor"})
    public String deleteAuthor(
            @ShellOption(defaultValue = "3") long id,
            @ShellOption(defaultValue = "Gianni Rodari") String fullName) {
        boolean result = authorDaoJdbc.deleteAuthor(new Author(id, fullName));
        return result ? "Author (id=" + id + " " + fullName + ") removed from the library"
                : "Error: Something went wrong!";
    }

    /**
     * Метод getAuthorById получает данные об авторе по его id (cRud)
     * Сокращенный вызов: "gabi", "getauthorbyid" --id id
     * Пример: gabi --id 2
     */
    @ShellMethod(value = "Getting information about the author from the library by id", key = {"gabi", "getAuthorById"})
    public String getAuthorById(
            @ShellOption(defaultValue = "2") long id) {
        Author result = authorDaoJdbc.getAuthorById(id);
        return result == null ? "Author not found!" : result.toString();
    }

    /**
     * Метод getAllAuthors получает список всех авторов из библиотеки (cRud)
     * Сокращенный вызов: "gaa", "getallauthors"
     * Пример: getallauthors
     */
    @ShellMethod(value = "Getting a list of all authors from the library", key = {"gaa", "getallauthors"})
    public String getAllAuthors() {
        List<Author> result = authorDaoJdbc.getAllAuthors();
        return result.size() == 0 ? "Authors not found!" : result.toString();
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
        long id = bookDaoJdbc.createBook(new Book(title, new Author(author), new Genre(genre)));
        return "The book (" + id + ") " + title + ", " + author + " (" + genre + ") has been successfully entered!";
    }

    /**
     * Метод getBookById (cRud)
     * Сокращенный вызов: "gbbi", "getbookbyid" --id id
     * Пример: gbbi --id 1
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Get book data by its id", key = {"gbbi", "getbookbyid"})
    public String getBookById(@ShellOption(defaultValue = "1") long id) {
        return bookDaoJdbc.getBookById(id).toString();
    }

    /**
     * Метод updateBookById обновляет данные по книге: название, автора, жанр (crUd)
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
                                 @ShellOption(defaultValue = "New Title") String title,
                                 @ShellOption(defaultValue = "New Author") String author,
                                 @ShellOption(defaultValue = "New Genre") String genre) {
        return bookDaoJdbc.updateBookById(id, new Book(title, new Author(author), new Genre(genre))) ? "The book id="
                + id + " has " + "been updated (title: " + title + ", author: " + author + ", genre: " + genre + ")"
                : "Error: Something went wrong!";
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
                "wrong!";
    }

    /**
     * Метод getAllBook (cRud)
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
     * Метод getIdByBook возвращает id для книги, если она есть в библиотеке (cRud)
     * Поиск выполняется только по названию, автору и жанру книги
     * Сокращенный вызов: "gibb", "getidbybook" --title title --fullName fullName --genre name
     * Пример: gibb --title 'The Pilgrim’s Progress' --fullName 'John Bunyan' --name 'History'
     */
    @ShellMethod(value = "Getting an id by book", key = {"gibb", "getidbybook"})
    public String getIdByBook(@ShellOption(defaultValue = "The Pilgrim’s Progress") String title,
                              @ShellOption(defaultValue = "John Bunyan") String fullName,
                              @ShellOption(defaultValue = "History") String name) {
        String fullNameBook = title + " " + fullName + " (" + name + ")";
        long id = bookDaoJdbc.getIdByBook(new Book(title, new Author(fullName), new Genre(name)));
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
