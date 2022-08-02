package ru.otus.spring06books.shell;

import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06books.entities.Author;
import ru.otus.spring06books.entities.Book;
import ru.otus.spring06books.entities.Comment;
import ru.otus.spring06books.entities.Genre;
import ru.otus.spring06books.repositories.AuthorRepositoryJpa;
import ru.otus.spring06books.repositories.BookRepositoryJpa;
import ru.otus.spring06books.repositories.CommentRepositoryJpa;
import ru.otus.spring06books.repositories.GenreRepositoryJpa;

import java.sql.SQLException;
import java.util.List;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {

    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;
    private final CommentRepositoryJpa commentRepositoryJpa;
    private final BookRepositoryJpa bookRepositoryJpa;

    /**
     * Конструктор класса
     *
     * @param authorRepositoryJpa
     * @param genreRepositoryJpa
     * @param commentRepositoryJpa
     * @param bookRepositoryJpa
     */
    @Autowired
    public AppEventsCommands(AuthorRepositoryJpa authorRepositoryJpa, GenreRepositoryJpa genreRepositoryJpa, CommentRepositoryJpa commentRepositoryJpa, BookRepositoryJpa bookRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
        this.commentRepositoryJpa = commentRepositoryJpa;
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    /**
     * Метод aboutLibrary выводит пользователю строку с приглашением и числом книг в библиотеке
     * Аннотация @Transactional - метод изменяет данные
     * Сокращенный вызов: "a", "about"
     *
     * @return
     */
    @Transactional
    @ShellMethod(value = "Information about the library", key = {"a", "about"})
    public String aboutLibrary() {
        long countOfBooks = bookRepositoryJpa.getCountOfBooks();
        long countOfAuthors = authorRepositoryJpa.getCountOfAuthors();
        long countOfGenres = genreRepositoryJpa.getCountOfGenres();
        long countOfComment = commentRepositoryJpa.getCountOfComment();

        return "Welcome to our library! We have more than " + countOfBooks
                + " books by " + countOfAuthors + " authors and " + countOfGenres + " genres in our library. " +
                "Our readers have left more than " + countOfComment + " comments on the books!";
    }

    /**
     * Метод createNewAuthor (Crud)
     * Аннотация @Transactional - метод изменяет данные
     * Сокращенный вызов: "ca", "createauthor" --name author_fullname
     * Пример: ca --fullName 'Stephen Edwin King'
     *
     * @param fullName
     * @return
     */
    @Transactional
    @ShellMethod(value = "Create a new Author of books in the library", key = {"ca", "createauthor"})
    public String createNewAuthor(@ShellOption(defaultValue = "Stephen Edwin King") String fullName) {
        long id = authorRepositoryJpa.createAuthor(new Author(fullName)).getId();
        return id != 0 ? "New author (" + id + ") " + fullName + " has been successfully created!" : "Error: Something went wrong!";
    }

    /**
     * Метод getAuthorById получает данные об авторе по его id (cRud)
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Сокращенный вызов: "gabi", "getauthorbyid" --id id
     * Пример: gabi --id 2
     * Метод не подразумевает изменения данных в БД, используется рекомендуемая аннотация @Transactional(readOnly = true)
     */
    @Transactional(readOnly = true)
    @ShellMethod(value = "Getting information about the author from the library by id", key = {"gabi", "getAuthorById"})
    public String getAuthorById(
            @ShellOption(defaultValue = "2") long id) {
        Author result = authorRepositoryJpa.getAuthorById(1);
        return result == null ? "Author not found!" : result.toString();
    }

    /**
     * Метод getIdByAuthor возвращает id для полного имени данного автора, если он есть в библиотеке
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Сокращенный вызов: "giba", "getidbyauthor" --fullName author_fullname
     * Пример: giba --fullName 'Daniel Defoe'
     * Метод не подразумевает изменения данных в БД, используется рекомендуемая аннотация @Transactional(readOnly = true)
     */
    @Transactional(readOnly = true)
    @ShellMethod(value = "Getting an id by author", key = {"giba", "getidbyauthor"})
    public String authorRepositoryJpa(@ShellOption(defaultValue = "Daniel Defoe") String fullName) {
        long id = authorRepositoryJpa.getIdByAuthor(new Author(fullName));
        return id == 0 ? "Author '" + fullName + "' not found in the library!" : "Author '" + fullName + "' has an id=" + id;
    }

    /**
     * Метод updateAuthor обновляет данные об авторе в библиотеке (crUd)
     * Аннотация @Transactional - метод изменяет данные
     * Сокращенный вызов: "ua", "updateauthor" --id id --fullName full_name
     * Пример: ua --id 1 --fullName 'Gianni Rodari'
     */
    @Transactional
    @ShellMethod(value = "Updating information about the author", key = {"ua", "updateauthor"})
    public String updateAuthor(
            @ShellOption(defaultValue = "1") long id,
            @ShellOption(defaultValue = "Gianni Rodari") String fullName) {
        boolean result = authorRepositoryJpa.updateAuthor(new Author(id, fullName));
        return result ? "Information about the author (" + "id=" + id + " " + fullName + ") has been updated!"
                : "Update error: Something went wrong!";
    }

    /**
     * Метод deleteAuthor удаляет данные об авторе в библиотеке (cruD)
     * Аннотация @Transactional - метод изменяет данные
     * Сокращенный вызов: "da", "deleteauthor" --id id --fullName full_name
     * Пример: da --id 3 --fullName 'Gianni Rodari'
     */
    @Transactional
    @ShellMethod(value = "Deleting author data from the library", key = {"da", "deleteauthor"})
    public String deleteAuthor(
            @ShellOption(defaultValue = "3") long id,
            @ShellOption(defaultValue = "Gianni Rodari") String fullName) {
        boolean result = authorRepositoryJpa.deleteAuthor(new Author(id, fullName));
        return result ? "Author (id=" + id + " " + fullName + ") removed from the library"
                : "Delete error: Something went wrong!";
    }

    /**
     * Метод getAllAuthors получает список всех авторов из библиотеки (cRud)
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Сокращенный вызов: "gaa", "getallauthors"
     * Пример: getallauthors
     */
    @Transactional(readOnly = true)
    @ShellMethod(value = "Getting a list of all authors from the library", key = {"gaa", "getallauthors"})
    public String getAllAuthors() {
        List<Author> result = authorRepositoryJpa.getAllAuthors();
        return result.size() == 0 ? "Authors not found!" : result.toString();
    }

    /**
     * Метод createGenre - создает наименование нового жанра книг в библиотеке
     * Аннотация @Transactional - метод изменяет данные
     * Сокращенный вызов: "cg", "creategenre" --name genre_name
     * Пример: cg --name Novel
     *
     * @param name
     * @return
     */
    @Transactional
    @ShellMethod(value = "Create a new genre of books in the library", key = {"cg", "creategenre"})
    public String createGenre(@ShellOption(defaultValue = "Novel") String name) {
        long id = genreRepositoryJpa.createGenre(new Genre(name));
        return "New genre (" + id + ") " + name + " has been successfully created!";
    }

    /**
     * Метод getIdByGenre возвращает id для жанра, если он есть в библиотеке
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Сокращенный вызов: "gibg", "getidbygenre" --name genre_name
     * Пример: gibg --name 'History'
     */
    @Transactional(readOnly = true)
    @ShellMethod(value = "Getting a genre id", key = {"gibg", "getidbygenre"})
    public String getIdByGenre(@ShellOption(defaultValue = "History") String name) {
        long id = genreRepositoryJpa.getIdByGenre(new Genre(name));
        return id == 0 ? "Genre '" + name + "' not found in the library!" : "Genre '" + name + "' has an id=" + id;
    }

    /**
     * Метод updateGenre обновляет данные о жанре в библиотеке
     * Аннотация @Transactional - метод изменяет данные
     * Сокращенный вызов: "ug", "updategenre" --id id --name name
     * Пример: ug --id 1 --name 'Politics'
     */
    @Transactional
    @ShellMethod(value = "Updating information about the genre", key = {"ug", "updategenre"})
    public String updateGenre(
            @ShellOption(defaultValue = "1") long id,
            @ShellOption(defaultValue = "Politics") String name) {
        boolean result = genreRepositoryJpa.updateGenre(new Genre(id, name));
        return result ? "Information about the genre (" + "id=" + id + " " + name + ") has been updated!"
                : "Update error: Something went wrong!";
    }

    /**
     * Метод deleteGenre удаляет данные о жанре в библиотеке
     * Аннотация @Transactional - метод изменяет данные
     * Сокращенный вызов: "dg", "deletegenre" --id id --name name
     * Пример: dg --id 5 --name 'Fiction'
     */
    @Transactional
    @ShellMethod(value = "Deleting genre data from the library", key = {"dg", "deletegenre"})
    public String deleteGenre(
            @ShellOption(defaultValue = "5") long id,
            @ShellOption(defaultValue = "Fiction") String name) {
        boolean result = genreRepositoryJpa.deleteGenre(new Genre(id, name));
        return result ? "Genre (id=" + id + " " + name + ") removed from the library"
                : "Delete error: Something went wrong!";
    }

    /**
     * Метод getGenreById получает данные о жанре по его id
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Сокращенный вызов: "ggbi", "getgenrebyid" --id id
     * Пример: ggbi --id 2
     */
    @Transactional(readOnly = true)
    @ShellMethod(value = "Getting information about the author from the library by id", key = {"ggbi", "getgenrebyid"})
    public String getGenreById(
            @ShellOption(defaultValue = "2") long id) {
        Genre result = genreRepositoryJpa.getGenreById(id);
        return result == null ? "Genre not found!" : result.toString();
    }

    /**
     * Метод getAllGenres получает список всех жанров из библиотеки (cRud)
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Сокращенный вызов: "gag", "getallgenres"
     * Пример: getallgenres
     */
    @Transactional(readOnly = true)
    @ShellMethod(value = "Getting a list of all genres from the library", key = {"gag", "getallgenres"})
    public String getAllGenres() {
        List<Genre> result = genreRepositoryJpa.getAllGenres();
        return result.size() == 0 ? "Genres not found!" : result.toString();
    }

    /**
     * Метод createNewBook (Crud)
     * Аннотация @Transactional - метод изменяет данные
     * Сокращенный вызов: "cb", "createbook" --title title_book --author author --genre genre
     * Пример: cb --title 'A Life in Letters' --author 'Arthur Conan Doyle' --genre Autobiography
     *
     * @param title
     * @param author
     * @param genre
     * @return
     */
    @Transactional
    @ShellMethod(value = "Add information about a new book, author, genre to the library", key = {"cb", "createbook"})
    public String createNewBook(@ShellOption(defaultValue = "A Life in Letters") String title,
                                @ShellOption(defaultValue = "Arthur Conan Doyle") String author,
                                @ShellOption(defaultValue = "Autobiography") String genre) {
        long id = bookRepositoryJpa.createBook(new Book(title, new Author(author), new Genre(genre)));
        return "The book (" + id + ") " + title + ", " + author + " (" + genre + ") has been successfully entered!";
    }

    /**
     * Метод deleteBookById (cruD)
     * Аннотация @Transactional - метод изменяет данные
     * Сокращенный вызов: "db", "deletebook" --id id
     * Пример: db --id 1
     *
     * @param id
     * @return
     */
    @Transactional
    @ShellMethod(value = "Deleting the selected book by id", key = {"db", "deletebook"})
    public String deleteBookById(@ShellOption(defaultValue = "1") long id) {
        return bookRepositoryJpa.deleteBookById(id) ? "The book id=" + id + " has been deleted" : "Error: Something went " +
                "wrong!";
    }

    /**
     * Метод getIdByBook возвращает id для книги, если она есть в библиотеке (cRud)
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Поиск выполняется только по названию, автору и жанру книги
     * Сокращенный вызов: "gibb", "getidbybook" --title title --fullName fullName --genre name
     * Пример: gibb --title 'The Pilgrim’s Progress' --fullName 'John Bunyan' --name 'History'
     */
    @Transactional(readOnly = true)
    @ShellMethod(value = "Getting an id by book", key = {"gibb", "getidbybook"})
    public String getIdByBook(@ShellOption(defaultValue = "The Pilgrim’s Progress") String title,
                              @ShellOption(defaultValue = "John Bunyan") String fullName,
                              @ShellOption(defaultValue = "History") String name) {
        String fullNameBook = title + " " + fullName + " (" + name + ")";
        long id = bookRepositoryJpa.getIdByBook(new Book(title, new Author(fullName), new Genre(name)));
        return id == 0 ? "Book '" + fullNameBook + "' not found in the library!" : "Book '" + fullNameBook + "' has an id=" + id;
    }

    /**
     * Метод getBookById возвращает книгу по ее id (cRud)
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Сокращенный вызов: "gbbi", "getbookbyid" --id id
     * Пример: gbbi --id 1
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @ShellMethod(value = "Get book data by its id", key = {"gbbi", "getbookbyid"})
    public String getBookById(@ShellOption(defaultValue = "1") long id) {
        return bookRepositoryJpa.getBookById(id).toString();
    }

    /**
     * Метод updateBookById обновляет данные по книге: название, автора, жанр (crUd)
     * Аннотация @Transactional - метод изменяет данные
     * Сокращенный вызов: "ub", "updatebook" --id id --title new_title --author new_author --genre new_genre
     * Пример: ub --id 1 --title 'New title' --author 'New Author' --genre 'New Genre'
     *
     * @param id
     * @param title
     * @param author
     * @param genre
     * @return
     */
    @Transactional
    @ShellMethod(value = "Update book data by id", key = {"ub", "updatebook"})
    public String updateBookById(@ShellOption(defaultValue = "1") long id,
                                 @ShellOption(defaultValue = "New Title") String title,
                                 @ShellOption(defaultValue = "New Author") String author,
                                 @ShellOption(defaultValue = "New Genre") String genre) {
        return bookRepositoryJpa.updateBookById(id, new Book(title, new Author(author), new Genre(genre))) ? "The book id="
                + id + " has " + "been updated (title: " + title + ", author: " + author + ", genre: " + genre + ")"
                : "Error: Something went wrong!";
    }

    /**
     * Метод getAllBook возвращает все книги из библиотеки (cRud)
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Сокращенный вызов: "gab", "getallbook"
     * Пример: gab
     *
     * @return
     */
    @Transactional(readOnly = true)
    @ShellMethod(value = "Get a list of all library books", key = {"gab", "getallbook"})
    public String getAllBook() {
        List<Book> listBook = bookRepositoryJpa.getAllBooks();
        return "Received " + (listBook == null ? 0 : listBook.size()) + " books:  " + listBook.toString();
    }

    /**
     * Метод createComment создает новый комментарий (Crud)
     * Аннотация @Transactional - метод изменяет данные
     * Сокращенный вызов: "cc", "createcomment" --idBook idBook --comment commentText
     * Пример: cc --idBook 1 --comment 'I read the book with pleasure :)'
     *
     * @param idBook
     * @param comment
     * @return
     */
    @Transactional
    @ShellMethod(value = "Create a new book comment", key = {"cc", "createcomment"})
    public String createComment(@ShellOption(defaultValue = "1") long idBook,
                                @ShellOption(defaultValue = "I read the book with pleasure :)") String comment) {
        Comment newComment = new Comment(new Book(idBook), comment);
        long id = commentRepositoryJpa.createComment(newComment);
        return id != 0 ? "New comment (" + id + ") '" + comment + "' for book id=" + idBook + " has been successfully created!" : "Error: Something went wrong!";
    }

    /**
     * Метод deleteCommentById удаляет комментарий по id (cruD)
     * Аннотация @Transactional - метод изменяет данные
     * Сокращенный вызов: "dc", "deletecomment" --id id
     * Пример: dc --id 1
     *
     * @param id
     * @return
     */
    @Transactional
    @ShellMethod(value = "Deleting the selected comment by id", key = {"dc", "deletecomment"})
    public String deleteCommentById(@ShellOption(defaultValue = "1") long id) {
        return commentRepositoryJpa.deleteCommentById(id) ? "The comment id=" + id + " has been deleted" : "Error: Something went " +
                "wrong!";
    }

    /**
     * Метод getCommentById возвращает комментарий к книге по его id (cRud)
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Сокращенный вызов: "gcbi", "getcommentbyid" --id id
     * Пример: gcbi --id 1
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @ShellMethod(value = "Get comment by its id", key = {"gcbi", "getcommentbyid"})
    public String getCommentById(@ShellOption(defaultValue = "1") long id) {
        return commentRepositoryJpa.getCommentById(id).toString();
    }

    /**
     * Метод updateCommentById обновляет комментарий к книге по его id (crUd)
     * Аннотация @Transactional - метод изменяет данные
     * Сокращенный вызов: "uc", "updatecomment" --id id --comment new_comment
     * Пример: uc --id 1 --comment 'New comment'
     *
     * @param id
     * @param title
     * @param author
     * @param genre
     * @return
     */
    @Transactional
    @ShellMethod(value = "Update comment by id", key = {"uc", "updatecomment"})
    public String updateCommentById(@ShellOption(defaultValue = "1") long id,
                                    @ShellOption(defaultValue = "New comment") String comment) {
        return commentRepositoryJpa.updateComment(new Comment(id, comment, new Book())) ? "The comment id="
                + id + " has " + "been updated  )" : "Error: Something went wrong!";
    }

    // todo: +getAllComments

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
