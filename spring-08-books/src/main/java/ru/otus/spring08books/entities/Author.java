package ru.otus.spring08books.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Класс Автор книги
 */
@Document(collection = "author")
public class Author {
    @Id
    private String id;
    private String fullName;

    public Author() {

    }

    public Author(String fullName) {
        this.fullName = fullName;
    }

    public Author(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}