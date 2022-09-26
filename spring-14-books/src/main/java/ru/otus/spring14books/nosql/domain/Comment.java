package ru.otus.spring14books.nosql.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Класс Комментарий к книге
 */
@Document(collection = "comment")
public class Comment {
    @Id
    private String id;
    @Field(name = "comment_text")
    private String commentText;
    @DBRef
    private Book book;

    public Comment() {

    }

    public Comment(String commentText, Book book) {
        this.commentText = commentText;
        this.book = book;
    }

    public Comment(String id, String commentText, Book book) {
        this.id = id;
        this.commentText = commentText;
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
