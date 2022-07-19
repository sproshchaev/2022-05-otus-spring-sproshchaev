package ru.otus.spring05books.shell;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.SQLException;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {


    @ShellMethod(value = "Info", key = {"i", "info"})
    public String getInfo() {
        return "Welcome to the library!";
    }

    /**
     * Метод startConsoleH2
     * @return
     * @throws SQLException
     */
    @ShellMethod(value = "Start console H2", key = {"c", "console"})
    public void startConsoleH2() throws SQLException {
        Console.main();
    }


}
