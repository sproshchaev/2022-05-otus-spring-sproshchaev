package ru.otus.spring06books.models;

import javax.persistence.*;

/**
 * Класс Автор
 */
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "fullname")
    private String fullName;

    /**
     * Конструктор класса без параметров
     */
    public Author() {

    }

    /**
     * Конструктор класса с параметром fullName
     * @param fullName
     */
    public Author(String fullName) {
        this.fullName = fullName;
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
