package ru.otus.spring15cafe.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring15cafe.services.OrderService;
import ru.otus.spring15cafe.services.OrderServiceImpl;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {

    private final OrderService orderService;

    @Autowired
    public AppEventsCommands(OrderServiceImpl orderService) {
        this.orderService = orderService;
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
    public String makeOrder(@ShellOption(defaultValue = "Meat, 0, Cola, 1, Fish, 0, Сoffee, 1") String orderString) {
        orderService.generate(orderString);
        return "Order is ready!";
    }

}
