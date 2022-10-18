package ru.otus.spring06books.entities;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Класс Книга
 * Примечание: Использование @Table, @Column - хорошая практика, даже когда устраивает автонейминг!
 * Для полей-классов вместо @Column используется @JoinColumn()
 * Поле комментарии к книге
 *
 * @OneToMany - одна книга (Book.class) имеет множество комментариев
 * orphanRemoval = true ("удаление сирот") т.е. комментарии не живут без книги
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
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 10)
    private List<Comment> comments;

    public Book() {
    }

    public Book(long id) {
        this.id = id;
    }

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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        String commentsStr = "";
        if (comments != null) {
            for (int i = 0; i < comments.size(); i++) {
                commentsStr = commentsStr + (i + 1) + ") " + comments.get(i).getCommentText() + " ";
            }
        }
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", comments=" + commentsStr +
                '}';
    }
}
