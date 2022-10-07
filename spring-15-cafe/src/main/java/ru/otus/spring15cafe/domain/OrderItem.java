package ru.otus.spring15cafe.domain;

/**
 * Класс OrderItem состав позиций заказа
 */
public class OrderItem {
    private final String itemName;

    private final boolean barAssortment;

    public OrderItem(String itemName, boolean barAssortment) {
        this.itemName = itemName;
        this.barAssortment = barAssortment;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isBarAssortment() {
        return barAssortment;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemName='" + itemName + '\'' +
                ", barAssortment=" + barAssortment +
                '}';
    }
}
