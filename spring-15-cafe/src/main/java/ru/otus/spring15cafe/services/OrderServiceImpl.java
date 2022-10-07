package ru.otus.spring15cafe.services;

import org.springframework.stereotype.Service;
import ru.otus.spring15cafe.domain.Food;
import ru.otus.spring15cafe.domain.OrderItem;
import ru.otus.spring15cafe.gateway.Cafe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Класс OrderServiceImpl содержит методы формирования нового заказа
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final Cafe cafe;

    public OrderServiceImpl(Cafe cafe) {
        this.cafe = cafe;
    }

    public void generate(String orderString) {
        String[] itemArray = orderString.split(",");
        List<OrderItem> itemList = new ArrayList<>();
        for (int i = 0; i < itemArray.length; i++) {
            if ((i > 0) && (i % 2 != 0)) {
                itemList.add(new OrderItem(itemArray[i - 1].trim(), itemArray[i].contains("1")));
            }
        }
        Collection<Food> food = cafe.process(itemList);
    }
}
