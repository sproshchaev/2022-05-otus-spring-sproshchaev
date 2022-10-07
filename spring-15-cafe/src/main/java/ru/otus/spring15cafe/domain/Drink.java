package ru.otus.spring15cafe.domain;

/**
 * Класс Drink
 */
public class Drink {

    private final String drinkName;

    public Drink(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkName() {
        return drinkName;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "drinkName='" + drinkName + '\'' +
                '}';
    }

}
