package ru.otus.spring15cafe.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;
import ru.otus.spring15cafe.domain.Food;
import ru.otus.spring15cafe.domain.OrderItem;

import java.util.Collection;
@Component
@MessagingGateway
public interface Cafe {

    @Gateway(requestChannel = "itemsChannel", replyChannel = "readyChannel")
    Collection<Food> process(Collection<OrderItem> orderItem);
}
