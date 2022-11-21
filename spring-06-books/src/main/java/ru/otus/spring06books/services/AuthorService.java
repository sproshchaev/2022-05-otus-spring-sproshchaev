package ru.otus.spring06books.services;

public interface AuthorService {
    String createNewAuthor(String fullName);

    String getAuthorById(long id);

    String getIdByAuthor(String fullName);

    String updateAuthor(long id, String fullName);

    String deleteAuthor(long id, String fullName);

    String getAllAuthors();
}
