package ru.otus.spring15cafe.domain;

/**
 * Класс Food
 */
public class Food {
    private final String foodName;

    public Food(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodName() {
        return foodName;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodName='" + foodName + '\'' +
                '}';
    }

}
