package ru.otus.spring15cafe.services;

import org.springframework.stereotype.Service;

/**
 * Класс DeliveryService содержит методы доставки заказа
 */
@Service
public class DeliveryService {

    /**
     * Метод print формирует накладную к доставке
     * @param obj
     */
    public void print(Object obj) {
        System.out.println(obj.toString());
    }

    /**
     * Метод deliver осуществляет доставку заказа
     * @throws InterruptedException
     */
    public void deliver() throws InterruptedException {
        System.out.println("Order delivery ...");
        Thread.sleep(1000);
        System.out.println("Order has been delivered!");
    }

}
