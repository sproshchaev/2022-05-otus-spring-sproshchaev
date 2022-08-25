package ru.otus.spring09books.dto;

import ru.otus.spring09books.domain.Book;

/**
 * Класс BookDto
 */
public class BookDto {
    private String title;
    private String authorFullName;
    private String genreName;

    public BookDto() {
    }

    public BookDto(String title, String authorFullName, String genreName) {
        this.title = title;
        this.authorFullName = authorFullName;
        this.genreName = genreName;
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
                "title='" + title + '\'' +
                ", authorFullName='" + authorFullName + '\'' +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}