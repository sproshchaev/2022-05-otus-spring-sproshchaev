package ru.otus.spring15cafe.domain;

public class Drink {

    private final String drinkName;

    public Drink(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkName() {
        return drinkName;
    }
}
