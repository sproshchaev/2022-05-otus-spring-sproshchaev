package ru.otus.spring06books.entities;

import javax.persistence.*;

/**
 * Класс Жанр
 * Использование @Table, @Column - хорошая практика, даже когда устраивает автонейминг!
 * Для полей-классов вместо @Column используется @JoinColumn()
 */
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;

    /**
     * Конструктор класса без параметра
     */
    public Genre() {

    }

    /**
     * Конструктор класса с name
     *
     * @param name
     */
    public Genre(String name) {
        this.name = name;
    }

    /**
     * Конструктор класса с полями id, name
     *
     * @param id
     * @param name
     */
    public Genre(long id, String name) {
        this.id = id;
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
