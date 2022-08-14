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
    private final GenreServiceMongoDB genreServiceMongoDB;
    private final BookServiceMongoDb bookServiceMongoDb;
    private final CommentServiceMongoDb commentServiceMongoDb;
    private final LibraryServiceMongoDb libraryServiceMongoDb;

    @Autowired
    public AppEventsCommands(AuthorServiceMongoDb authorServiceMongoDB, GenreServiceMongoDB genreServiceMongoDB, BookServiceMongoDb bookServiceMongoDb, CommentServiceMongoDb commentServiceMongoDb, LibraryServiceMongoDb libraryServiceMongoDb) {
        this.authorServiceMongoDB = authorServiceMongoDB;
        this.genreServiceMongoDB = genreServiceMongoDB;
        this.bookServiceMongoDb = bookServiceMongoDb;
        this.commentServiceMongoDb = commentServiceMongoDb;
        this.libraryServiceMongoDb = libraryServiceMongoDb;
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
        return genreServiceMongoDB.createGenre(name);
    }

}
