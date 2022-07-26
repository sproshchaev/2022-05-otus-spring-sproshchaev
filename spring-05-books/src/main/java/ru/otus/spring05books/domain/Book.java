package ru.otus.spring05books.domain;

import org.springframework.stereotype.Component;

/**
 * Класс Книга
 */
public class Book {

    private long id;
    private String title;
    private Author author;
    private Genre genre;

    /**
     * Конструктор класса без id
     * @param title
     * @param author
     * @param genre
     */
    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    /**
     * Конструктор класса с id книги
     * @param id
     * @param title
     * @param author
     * @param genre
     */
    public Book(long id, String title, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }

}
