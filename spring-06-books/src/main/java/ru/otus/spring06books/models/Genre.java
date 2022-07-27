package ru.otus.spring06books.models;

/**
 * Класс Жанр
 */
public class Genre {

    private long id;
    private String name;

    /**
     * Конструктор класса без параметра
     */
    public Genre() {

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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
