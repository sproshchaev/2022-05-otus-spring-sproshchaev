package ru.otus.spring06books.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Класс Книга
 * Использование @Table, @Column - хорошая практика, даже когда устраивает автонейминг!
 * Для полей-классов вместо @Column используется @JoinColumn()
 *
 * todo (Работа над ошибками): у полей Author, Genre в аннотации @ManyToOne() убрана опция cascade = CascadeType.ALL
 *
 */
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "book-comments-entity-graph", attributeNodes = @NamedAttributeNode("comments"))
@NamedEntityGraph(name = "book-author-entity-graph", attributeNodes = @NamedAttributeNode("author"))
@NamedEntityGraph(name = "book-genre-entity-graph", attributeNodes = @NamedAttributeNode("genre"))
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;
    /**
     * Поле комментарии к книге
     *
     * @OneToMany - одна книга (Book.class) имеет множество комментариев
     * orphanRemoval = true ("удаление сирот") т.е. комментарии не живут без книги
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "book_id")
    @Fetch(FetchMode.SUBSELECT)
    private List<Comment> comments;

    /**
     * Конструктор класса без параметров
     */
    public Book() {

    }

    /**
     * Конструктор класса c параметром id
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

    /**
     * Конструктор класса с параметрами id, title, author, genre
     *
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

    /**
     * Метод toString()
     * Выводит первый комментарий к книге
     *
     * @return
     */
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
