package ru.otus.spring14books.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {

    /**
     * Метод about выводит информацию о приложении
     * Сокращенный вызов: "a", "about"
     *
     * @return
     */
    @ShellMethod(value = "Information about the application", key = {"a", "about"})
    public String aboutApplication() {
        return "Welcome to Spring Batch Application!";
    }
}
