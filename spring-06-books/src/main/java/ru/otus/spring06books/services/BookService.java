package ru.otus.spring06books.services;

public interface BookService {
    String createNewBook(String title, String author, String genre);

    String deleteBookById(long id);

    String getIdByBook(String title, String fullName, String name);

    String getBookById(long id);

    String updateBookById(long id, String title, String author, String genre);

    String getAllBook();
}
