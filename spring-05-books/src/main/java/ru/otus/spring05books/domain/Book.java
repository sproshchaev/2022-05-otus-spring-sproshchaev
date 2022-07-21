package ru.otus.spring05books.domain;

/**
 * Класс Книга
 */
public class Book {

    private long id;
    private final String title;
    private final Author author;
    private final Genre genre;

    /**
     * Конструктор класса
     * @param title
     * @param author
     * @param genre
     */
    public Book(String title, Author author, Genre genre) {
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
}
