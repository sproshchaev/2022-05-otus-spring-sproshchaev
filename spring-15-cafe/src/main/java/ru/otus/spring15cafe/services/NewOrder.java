package ru.otus.spring15cafe.services;

import org.springframework.stereotype.Service;
import ru.otus.spring15cafe.domain.Food;
import ru.otus.spring15cafe.domain.OrderItem;
import ru.otus.spring15cafe.gateway.Cafe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class NewOrder {

    private final Cafe cafe;

    public NewOrder(Cafe cafe) {
        this.cafe = cafe;
    }

    public void generate() {

        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem("1", false));
        items.add(new OrderItem("2", false));
        items.add(new OrderItem("3", true));

        Collection<Food> food = cafe.process(items);

    }

}
