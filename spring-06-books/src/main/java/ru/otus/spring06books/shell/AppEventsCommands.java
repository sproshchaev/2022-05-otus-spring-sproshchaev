package ru.otus.spring06books.shell;

import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring06books.entities.Comment;
import ru.otus.spring06books.services.*;

import java.sql.SQLException;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд Spring Shell (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {
    private final LibraryService libraryService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final CommentService commentService;

    @Autowired
    public AppEventsCommands(LibraryService libraryService, AuthorService authorService, GenreService genreService,
                             BookService bookService, CommentService commentService) {
        this.libraryService = libraryService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookService = bookService;
        this.commentService = commentService;
    }

    /**
     * Метод aboutLibrary выводит пользователю строку с приглашением и информацией о библиотеке
     * Сокращенный вызов: "a", "about"
     * Пример: a
     *
     * @return
     */
    @ShellMethod(value = "Information about the library", key = {"a", "about"})
    public String aboutLibrary() {
        return libraryService.aboutLibrary();
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
     * Метод deleteAuthor удаляет данные об авторе в библиотеке (cruD)
     * Сокращенный вызов: "da", "deleteauthor" --id id --fullName full_name
     * Пример: da --id 3 --fullName 'Gianni Rodari'
     *
     * @param id
     * @param fullName
     * @return
     */
    @ShellMethod(value = "Deleting author data from the library", key = {"da", "deleteauthor"})
    public String deleteAuthor(
            @ShellOption(defaultValue = "3") long id,
            @ShellOption(defaultValue = "Gianni Rodari") String fullName) {
        return authorService.deleteAuthor(id, fullName);
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
     * Метод deleteGenre удаляет данные о жанре в библиотеке
     * Сокращенный вызов: "dg", "deletegenre" --id id --name name
     * Пример: dg --id 5 --name 'Fiction'
     *
     * @param id
     * @param name
     * @return
     */
    @ShellMethod(value = "Deleting genre data from the library", key = {"dg", "deletegenre"})
    public String deleteGenre(
            @ShellOption(defaultValue = "5") long id,
            @ShellOption(defaultValue = "Fiction") String name) {
        return genreService.deleteGenre(id, name);
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
     * Метод deleteBookById (cruD)
     * Сокращенный вызов: "db", "deletebook" --id id
     * Пример: db --id 1
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Deleting the selected book by id", key = {"db", "deletebook"})
    public String deleteBookById(@ShellOption(defaultValue = "1") long id) {
        return bookService.deleteBookById(id);
    }

    /**
     * Метод getIdByBook возвращает id для книги, если она есть в библиотеке (cRud)
     * Поиск выполняется по названию, автору и жанру книги
     * Сокращенный вызов: "gibb", "getidbybook" --title title --fullName fullName --genre name
     * Пример: gibb --title 'The Pilgrim’s Progress' --fullName 'John Bunyan' --name 'History'
     *
     * @param title
     * @param fullName
     * @param name
     * @return
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
        return bookService.updateBookById(id, title, author, genre);
    }

    /**
     * Метод getAllBook возвращает все книги из библиотеки (cRud)
     * Сокращенный вызов: "gab", "getallbook"
     * Пример: gab
     *
     * @return
     */
    @ShellMethod(value = "Get a list of all library books", key = {"gab", "getallbook"})
    public String getAllBook() {
        return bookService.getAllBook();
    }

    /**
     * Метод createComment создает новый комментарий (Crud)
     * Сокращенный вызов: "cc", "createcomment" --idBook idBook --comment commentText
     * Пример: cc --idBook 1 --comment 'I read the book with pleasure :)'
     *
     * @param idBook
     * @param comment
     * @return
     */
    @ShellMethod(value = "Create a new book comment", key = {"cc", "createcomment"})
    public String createComment(@ShellOption(defaultValue = "1") long idBook,
                                @ShellOption(defaultValue = "I read the book with pleasure :)") String comment) {
        return commentService.createComment(idBook, comment);
    }

    /**
     * Метод deleteCommentById удаляет комментарий по id (cruD)
     * Сокращенный вызов: "dc", "deletecomment" --id id
     * Пример: dc --id 1
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Deleting the selected comment by id", key = {"dc", "deletecomment"})
    public String deleteCommentById(@ShellOption(defaultValue = "1") long id) {
        return commentService.deleteCommentById(id);
    }

    /**
     * Метод getCommentById возвращает комментарий к книге по его id (cRud)
     * Сокращенный вызов: "gcbi", "getcommentbyid" --id id
     * Пример: gcbi --id 1
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Get comment by its id", key = {"gcbi", "getcommentbyid"})
    public String getCommentById(@ShellOption(defaultValue = "1") long id) {
        return commentService.getCommentById(id);
    }

    /**
     * Метод getAllCommentsBookById возвращает все комментарии к книге
     * Сокращенный вызов: "gacbbi", "getallcommentsbookbyid" --id idBook
     * Пример: gacbbi --id 1
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Get all comments on the book by id", key = {"gacbbi", "getallcommentsbookbyid"})
    public String getAllCommentsBookById(@ShellOption(defaultValue = "1") long id) {
        return commentService.getAllCommentsBookById(id);
    }

    /**
     * Метод getIdByComment возвращает id для комментария (cRud)
     * Сокращенный вызов: "gibс", "getidbycomment" --comment comment_text
     * Пример: gibс --comment 'The Pilgrims Progress — is a very interesting book!'
     *
     * @param comment
     * @return
     */
    @ShellMethod(value = "Getting an id by comment", key = {"gibс", "getidbycomment"})
    public String getIdByComment(@ShellOption(defaultValue = "The Pilgrims Progress — is a very interesting book!") String comment) {
        return commentService.getIdByComment(new Comment(comment));
    }

    /**
     * Метод updateCommentById обновляет комментарий к книге по его id (crUd)
     * Сокращенный вызов: "uc", "updatecomment" --id id --comment new_comment
     * Пример: uc --id 1 --comment 'New comment'
     *
     * @param id
     * @param comment
     * @return
     */
    @ShellMethod(value = "Update comment by id", key = {"uc", "updatecomment"})
    public String updateCommentById(@ShellOption(defaultValue = "1") long id,
                                    @ShellOption(defaultValue = "New comment") String comment) {
        return commentService.updateCommentById(id, comment);
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
