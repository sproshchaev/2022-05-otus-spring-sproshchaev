package ru.otus.spring15cafe.domain;

public class OrderItem {
    private final String itemName;

    private final boolean isIced;


    public OrderItem(String itemName, boolean isIced) {
        this.itemName = itemName;
        this.isIced = isIced;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isIced() {
        return isIced;
    }
}
