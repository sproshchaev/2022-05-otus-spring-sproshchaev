package ru.otus.spring15cafe.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.spring15cafe.domain.Drink;
import ru.otus.spring15cafe.domain.OrderItem;

/**
 * Класс BarService содержит методы обработки заказа в Баре
 */
@Service
public class BarService {

    /**
     * Метод drink производит обработку OrderItem и возвращает экземпляр класса Drink
     * @param orderItem
     * @return
     */
    public Drink drink(OrderItem orderItem) throws InterruptedException {
        System.out.println("Pour " + orderItem.getItemName() + " ...");
        Thread.sleep(1000);
        System.out.println(orderItem.getItemName() + " is poured");
        return new Drink(orderItem.getItemName());
    }

}
