package ru.otus.spring15cafe.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring15cafe.domain.OrderItem;
import ru.otus.spring15cafe.services.BarService;
import ru.otus.spring15cafe.services.NewOrder;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {

    private final NewOrder newOrder;

    private final BarService barService;

    public AppEventsCommands(NewOrder newOrder, BarService barService) {
        this.newOrder = newOrder;
        this.barService = barService;
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

    @ShellMethod(value = "Get drink", key = {"gd", "getdrink"})
    public String getDrink() throws Exception {
        barService.drink(new OrderItem("Gin", true));
        return "";
    }

}
