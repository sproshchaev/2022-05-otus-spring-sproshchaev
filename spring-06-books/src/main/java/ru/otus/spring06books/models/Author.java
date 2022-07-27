package ru.otus.spring06books.models;

/**
 * Класс Автор
 */
public class Author {
    private long id;
    private String fullName;

    /**
     * Конструктор класса без параметров
     */
    public Author() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
