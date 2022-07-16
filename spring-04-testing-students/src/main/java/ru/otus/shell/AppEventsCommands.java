package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 *
 */
@ShellComponent
public class AppEventsCommands {

    /**
     * Метод add
     * @return
     */
    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }
}
