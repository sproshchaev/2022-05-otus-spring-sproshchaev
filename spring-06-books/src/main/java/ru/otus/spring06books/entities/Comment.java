package ru.otus.spring06books.entities;

import javax.persistence.*;

/**
 * Класс Комментарий к книге
 * Использование @Table, @Column - хорошая практика, даже когда устраивает автонейминг!
 * Для полей-классов вместо @Column используется @JoinColumn()
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

    public Comment() {
    }

    public Comment(String commentText) {
        this.commentText = commentText;
    }

    public Comment(Book book, String commentText) {
        this.book = book;
        this.commentText = commentText;
    }

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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                ", book=" + book + '}';
    }
}
