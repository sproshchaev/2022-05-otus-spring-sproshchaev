package ru.otus.spring05books.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {

    @ShellMethod(value = "Info", key = {"i", "info"})
    public String info() {
        return "Welcome to the library!";
    }

}
