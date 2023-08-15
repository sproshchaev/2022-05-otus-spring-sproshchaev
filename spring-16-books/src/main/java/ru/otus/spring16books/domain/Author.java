package ru.otus.spring16books.domain;

import javax.persistence.*;

/**
 * Класс Автор (POJO)
 */
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
     *
     * @param fullName
     */
    public Author(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Конструктор класса с параметрами id, fullName
     *
     * @param id
     * @param fullName
     */
    public Author(long id, String fullName) {
        this.id = id;
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