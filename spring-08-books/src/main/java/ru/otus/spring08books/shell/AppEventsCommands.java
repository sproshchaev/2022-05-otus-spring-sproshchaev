package ru.otus.spring08books.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring08books.services.*;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {
    private final AuthorServiceMongoDb authorServiceMongoDB;
    private final GenreServiceMongoDb genreServiceMongoDb;
    private final BookServiceMongoDb bookServiceMongoDb;
    private final CommentServiceMongoDb commentServiceMongoDb;
    private final LibraryServiceMongoDb libraryServiceMongoDb;

    @Autowired
    public AppEventsCommands(AuthorServiceMongoDb authorServiceMongoDB, GenreServiceMongoDb genreServiceMongoDb, BookServiceMongoDb bookServiceMongoDb, CommentServiceMongoDb commentServiceMongoDb, LibraryServiceMongoDb libraryServiceMongoDb) {
        this.authorServiceMongoDB = authorServiceMongoDB;
        this.genreServiceMongoDb = genreServiceMongoDb;
        this.bookServiceMongoDb = bookServiceMongoDb;
        this.commentServiceMongoDb = commentServiceMongoDb;
        this.libraryServiceMongoDb = libraryServiceMongoDb;
    }

    /**
     * Метод aboutLibrary выводит информацию о числе авторов, книг и жанров в библиотеке
     * Сокращенный вызов: "a", "about"
     *
     * @return
     */
    @ShellMethod(value = "Information about the library", key = {"a", "about"})
    public String aboutLibrary() {
        return libraryServiceMongoDb.aboutLibrary();
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
        return authorServiceMongoDB.createNewAuthor(fullName);
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
        return authorServiceMongoDB.getIdByAuthor(fullName);
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
    public String getAuthorById(@ShellOption(defaultValue = "2") String id) {
        return authorServiceMongoDB.getAuthorById(id);
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
        return authorServiceMongoDB.getAllAuthors();
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
            @ShellOption(defaultValue = "1") String id,
            @ShellOption(defaultValue = "Gianni Rodari") String fullName) {
        return authorServiceMongoDB.updateAuthor(id, fullName);
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
            @ShellOption(defaultValue = "3") String id) {
        return authorServiceMongoDB.deleteAuthorById(id);
    }

    /**
     * Метод createNewBook (Crud)
     * Сокращенный вызов: "cb", "createbook" --title title_book --author author --genre genre
     * Пример: cb --title 'A Life in Letters' --author 'Arthur Conan Doyle' --genre 'Autobiography'
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
        return bookServiceMongoDb.createNewBook(title, author, genre);
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
        return bookServiceMongoDb.getIdByBook(title, fullName, name);
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
    public String getBookById(@ShellOption(defaultValue = "1") String id) {
        return bookServiceMongoDb.getBookById(id);
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
        return bookServiceMongoDb.getAllBook();
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
    public String updateBookById(@ShellOption(defaultValue = "1") String id,
                                 @ShellOption(defaultValue = "New Title") String title,
                                 @ShellOption(defaultValue = "New Author") String author,
                                 @ShellOption(defaultValue = "New Genre") String genre) {
        return bookServiceMongoDb.updateBookById(id, title, author, genre);
    }

    /**
     * Метод deleteBookById (cruD)
     * Сокращенный вызов: "dbbi", "deletebookbyid" --id id
     * Пример: dbbi --id 1
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Deleting the selected book by id", key = {"dbbi", "deletebookbyid"})
    public String deleteBookById(@ShellOption(defaultValue = "1") String id) {
        return bookServiceMongoDb.deleteBookById(id);
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
    public String createComment(@ShellOption(defaultValue = "1") String idBook,
                                @ShellOption(defaultValue = "I read the book with pleasure :)") String comment) {
        return commentServiceMongoDb.createCommentByIdBook(idBook, comment);
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
    public String getCommentById(@ShellOption(defaultValue = "1") String id) {
        return commentServiceMongoDb.getCommentById(id);
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
    public String getAllCommentsBookById(@ShellOption(defaultValue = "1") String id) {
        return commentServiceMongoDb.getAllCommentsBookById(id);
    }

    /**
     * Метод updateCommentById обновляет комментарий к книге по его id (crUd)
     * Сокращенный вызов: "ucbi", "updatecommentbyid" --id id --comment new_comment
     * Пример: ucbi --id 1 --comment 'New comment'
     *
     * @param id
     * @param comment
     * @return
     */
    @ShellMethod(value = "Update comment by id", key = {"ucbi", "updatecommentbyid"})
    public String updateCommentById(@ShellOption(defaultValue = "1") String id,
                                    @ShellOption(defaultValue = "New comment") String comment) {
        return commentServiceMongoDb.updateCommentById(id, comment);
    }

    /**
     * Метод deleteCommentById удаляет комментарий по id (cruD)
     * Сокращенный вызов: "dcbi", "deletecommentbyid" --id id
     * Пример: dcbi --id 1
     *
     * @param id
     * @return
     */
    @ShellMethod(value = "Deleting the selected comment by id", key = {"dcbi", "deletecommentbyid"})
    public String deleteCommentById(@ShellOption(defaultValue = "1") String id) {
        return commentServiceMongoDb.deleteCommentById(id);
    }

    /**
     * Метод deleteAllCommentBook удаляет все комментарии по книге (cruD)
     * Сокращенный вызов: "dacb", "deleteallcommentbook" --idBook id
     * Пример: dacb --idBook 1
     *
     * @param idBook
     * @return
     */
    @ShellMethod(value = "Delete all comments to the book", key = {"dacb", "deleteallcommentbook"})
    public String deleteAllCommentBook(@ShellOption(defaultValue = "1") String idBook) {
        return commentServiceMongoDb.deleteAllCommentBook(idBook);
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
        return genreServiceMongoDb.createGenre(name);
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
        return genreServiceMongoDb.getIdByGenre(name);
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
            @ShellOption(defaultValue = "62f9e9c81c9657487b94d8c4") String id) {
        return genreServiceMongoDb.getGenreById(id);
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
        return genreServiceMongoDb.getAllGenres();
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
            @ShellOption(defaultValue = "1") String id,
            @ShellOption(defaultValue = "Politics") String name) {
        return genreServiceMongoDb.updateGenre(id, name);
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
    public String deleteGenreById(@ShellOption(defaultValue = "5") String id) {
        return genreServiceMongoDb.deleteGenreById(id);
    }
}