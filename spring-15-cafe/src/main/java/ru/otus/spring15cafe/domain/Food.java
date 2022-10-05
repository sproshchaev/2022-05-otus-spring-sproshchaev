package ru.otus.spring15cafe.domain;

public class Food {
    private final String foodName;

    public Food(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodName() {
        return foodName;
    }
}
