package ru.otus.spring15cafe.services;

import org.springframework.stereotype.Service;
import ru.otus.spring15cafe.domain.Food;
import ru.otus.spring15cafe.domain.OrderItem;

/**
 * Класс KitchenService содержит методы обработки заказа на Кухне
 */
@Service
public class KitchenService {

    public Food cook(OrderItem orderItem) throws InterruptedException {
        System.out.println("Cooking " + orderItem.getItemName() + " ...");
        Thread.sleep(1000);
        System.out.println(orderItem.getItemName() + " is cooked");
        return new Food(orderItem.getItemName());
    }

}
