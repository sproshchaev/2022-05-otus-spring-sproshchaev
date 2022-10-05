package ru.otus.spring15cafe.services;

import org.springframework.stereotype.Component;
import ru.otus.spring15cafe.domain.Drink;
import ru.otus.spring15cafe.domain.Food;
import ru.otus.spring15cafe.domain.OrderItem;

@Component
public class BarService {

    public Food drink(OrderItem orderItem) throws Exception {
        System.out.println("Drink " + orderItem.getItemName());
        //Thread.sleep(3000);
        System.out.println("Drink " + orderItem.getItemName() + " done");
        return new Food(orderItem.getItemName());
    }

    public Drink hotDrink(Food orderItem) throws Exception {
        System.out.println("hotDrink " + orderItem.getFoodName());
        //Thread.sleep(3000);
        System.out.println("hotDrink " + orderItem.getFoodName() + " done");
        return new Drink(orderItem.getFoodName());
    }


}
