package ru.otus.spring05books.domain;

/**
 * Класс Автор
 */
public class Author {

    private long id;
    private final String fullName;

    /**
     * Конструктор класса
     * @param fullName
     */
    public Author(String fullName) {
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setId(long id) {
        this.id = id;
    }
}
