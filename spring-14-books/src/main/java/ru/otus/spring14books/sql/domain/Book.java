package ru.otus.spring14books.sql.domain;

import javax.persistence.*;

/**
 * Класс Книга
 */
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "book-author-genre-entity-graph", attributeNodes = {@NamedAttributeNode("author"),
        @NamedAttributeNode("genre")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    /**
     * Конструктор класса без параметров
     */
    public Book() {
    }

    /**
     * Конструктор класса с параметром id
     *
     * @param id
     */
    public Book(long id) {
        this.id = id;
    }

    /**
     * Конструктор класса с параметрами title, author, genre
     *
     * @param title
     * @param author
     * @param genre
     */
    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

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

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}
