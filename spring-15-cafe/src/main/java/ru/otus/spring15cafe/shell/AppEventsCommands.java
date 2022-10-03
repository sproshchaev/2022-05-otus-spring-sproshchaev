package ru.otus.spring15cafe.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring15cafe.services.NewOrder;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {

    private final NewOrder newOrder;

    public AppEventsCommands(NewOrder newOrder) {
        this.newOrder = newOrder;
    }

    /**
     * Метод about выводит информацию о приложении
     * Сокращенный вызов: "a", "about"
     *
     * @return
     */
    @ShellMethod(value = "Information about the application", key = {"a", "about"})
    public String aboutApplication() {
        return "Welcome to Cafe!";
    }

    /**
     * Метод формирования заказа
     * Сокращенный вызов: "mo", "makeorder"
     *
     * @return
     */
    @ShellMethod(value = "Make an order", key = {"mo", "makeorder"})
    public String makeOrder() {
        newOrder.generate();
        return "Make an order...";
    }
}
