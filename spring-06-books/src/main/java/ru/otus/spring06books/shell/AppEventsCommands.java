package ru.otus.spring06books.shell;

import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06books.models.Author;
import ru.otus.spring06books.models.Genre;
import ru.otus.spring06books.repositories.AuthorRepositoryJpa;
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

    /**
     * Конструктор класса
     *
     * @param authorRepositoryJpa
     * @param genreRepositoryJpa
     */
    @Autowired
    public AppEventsCommands(AuthorRepositoryJpa authorRepositoryJpa, GenreRepositoryJpa genreRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
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
        long countOfBooks = 0; // todo: bookDaoJdbc.getCountOfBooks();
        long countOfAuthors = authorRepositoryJpa.getCountOfAuthors();
        long countOfGenres = genreRepositoryJpa.getCountOfGenres();
        return "Welcome to our library! We have more than " + countOfBooks
                + " books by " + countOfAuthors + " authors and " + countOfGenres + " genres in our library";
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

    // ----------------------

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
