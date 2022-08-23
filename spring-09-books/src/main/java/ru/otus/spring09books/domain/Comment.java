package ru.otus.spring09books.domain;

import javax.persistence.*;

/**
 * Класс Комментарий к книге (POJO)
 * Примечание: для связанных сущностей переопределение методов toString, equals, hashcode приводит к вытягиванию
 * связей из базы
 */
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "comment_text")
    private String commentText;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    /**
     * Конструктор класса без параметров
     */
    public Comment() {

    }

    /**
     * Конструктор класса с параметрами commentText, book
     *
     * @param commentText
     * @param book
     */
    public Comment(String commentText, Book book) {
        this.commentText = commentText;
        this.book = book;
    }

    /**
     * Конструктор класса с параметрами id, commentText, book
     *
     * @param id
     * @param commentText
     * @param book
     */
    public Comment(long id, String commentText, Book book) {
        this.id = id;
        this.commentText = commentText;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}