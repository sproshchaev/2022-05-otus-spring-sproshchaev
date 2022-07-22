package ru.otus.spring05books.domain;

/**
 * Класс Жанр
 */
public class Genre {
    private long id;
    private final String name;

    /**
     * Конструктор класса
     * @param name
     */
    public Genre(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
