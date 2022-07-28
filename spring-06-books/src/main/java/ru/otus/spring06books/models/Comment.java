package ru.otus.spring06books.models;

import javax.persistence.*;

/**
 * Класс Комментарий к книге
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String commentText;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    /**
     * Конструктор класса
     */
    public Comment() {
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                ", book=" + book +
                '}';
    }
}