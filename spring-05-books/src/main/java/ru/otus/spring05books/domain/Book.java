package ru.otus.spring05books.domain;

/**
 * Класс Книга
 */
public class Book {
    // private final long id;
    private final String title;
    private final String authorFullName;
    private final String genreName;

    /**
     * Конструктор класса
     * @param id
     * @param title
     * @param authorFullName
     * @param genreName
     */
    public Book(/*long id,*/ String title, String authorFullName, String genreName) {
        //this.id = id;
        this.title = title;
        this.authorFullName = authorFullName;
        this.genreName = genreName;
    }

    /*public long getId() {
        return id;
    }*/

    public String getTitle() {
        return title;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public String getGenreName() {
        return genreName;
    }
}
