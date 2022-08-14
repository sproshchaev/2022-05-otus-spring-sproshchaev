package ru.otus.spring08books.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Класс Жанр книги
 */
@Document(collection = "genre")
public class Genre {
    @Id
    private String id;
    private String name;

    public Genre() {

    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}