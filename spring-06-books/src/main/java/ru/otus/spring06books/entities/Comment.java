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
    /**
     * Поле книга, к которой оставляют комменатрии
     *
     * @ManyToOne - много комментариев (Comment.class) к одной книге
     * При создании таблицы comment необходимо указать для этого поля каскадное удаление "references book(id) on delete cascade"
     */
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    /**
     * Конструктор класса без параметров
     */
    public Comment() {
    }

    /**
     * Конструктор класса с параметрами book, commentText
     *
     * @param book
     * @param commentText
     */
    public Comment(Book book, String commentText) {
        this.book = book;
        this.commentText = commentText;
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
