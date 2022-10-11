package ru.otus.spring17books.dto;

import ru.otus.spring17books.domain.Book;

/**
 * Класс BookDto
 */
public class BookDto {

    private long id;
    private String title;
    private String authorFullName;
    private String genreName;

    public static BookDto fromDomainObject(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor().getFullName(), book.getGenre().getName());
    }

    public BookDto() {
    }

    public BookDto(String title, String authorFullName, String genreName) {
        this.title = title;
        this.authorFullName = authorFullName;
        this.genreName = genreName;
    }

    public BookDto(long id, String title, String authorFullName, String genreName) {
        this.id = id;
        this.title = title;
        this.authorFullName = authorFullName;
        this.genreName = genreName;
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

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorFullName='" + authorFullName + '\'' +
                ", genreName='" + genreName + '\'' +
                '}';
    }

}