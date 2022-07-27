package ru.otus.spring06books.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring06books.models.Author;
import ru.otus.spring06books.repositories.AuthorRepositoryJpa;

import java.sql.SQLException;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {

    private AuthorRepositoryJpa authorRepositoryJpa;

    /**
     * Конструктор класса
     * @param authorRepositoryJpa
     */
    @Autowired
    public AppEventsCommands(AuthorRepositoryJpa authorRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
    }

    /**
     * Метод getAuthorById получает данные об авторе по его id (cRud)
     * Сокращенный вызов: "gabi", "getauthorbyid" --id id
     * Пример: gabi --id 2
     */
    @ShellMethod(value = "Getting information about the author from the library by id", key = {"gabi", "getAuthorById"})
    public String getAuthorById(
            @ShellOption(defaultValue = "2") long id) {
        Author result = authorRepositoryJpa.findById(1);
        return result == null ? "Author not found!" : result.toString();
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
