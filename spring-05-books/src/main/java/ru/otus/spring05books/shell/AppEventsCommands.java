package ru.otus.spring05books.shell;

import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring05books.dao.BookDaoJdbc;
import ru.otus.spring05books.domain.Book;

import java.sql.SQLException;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    /**
     * Метод getInfo выводит пользователю строку с приглашением и числом книг в библиотеке
     *
     * @return
     */
    @ShellMethod(value = "Info", key = {"i", "info"})
    public String getInfo() {
        Long countOfBooks = bookDaoJdbc.getCountOfBook();
        return "Welcome to the library! We have " + countOfBooks + " books";
    }

    /**
     * Метод startConsoleH2 выводит консоль
     *
     * @return
     * @throws SQLException
     */
    @ShellMethod(value = "Start console H2", key = {"c", "console"})
    public void startConsoleH2() throws SQLException {
        Console.main();
    }

    /**
     * Метод createBook создает книгу: наименование, автор, жанр
     *
     * @throws SQLException
     */
    @ShellMethod(value = "Create book", key = {"cr", "create"})
    public String createBook(@ShellOption(defaultValue = "New_book") String title,
                             @ShellOption(defaultValue = "1") int author_id,
                             @ShellOption(defaultValue = "1") int genre_id) throws SQLException {

        bookDaoJdbc.createBook(new Book(title, "1", "1"));
        return "Book " + title + " " + author_id + " " + genre_id + " added!";
    }


}
