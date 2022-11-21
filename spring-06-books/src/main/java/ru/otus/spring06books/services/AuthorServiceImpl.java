package ru.otus.spring06books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06books.entities.Author;
import ru.otus.spring06books.repositories.AuthorRepositoryJpa;

import java.util.List;

/**
 * Класс AuthorServiceImpl содержит методы сервиса работы с репозиторием Авторов библиотеки
 */
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    public AuthorServiceImpl(AuthorRepositoryJpa authorRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
    }

    @Override
    @Transactional
    public String createNewAuthor(String fullName) {
        long id = authorRepositoryJpa.createAuthor(new Author(fullName)).getId();
        return id != 0 ? "New author (" + id + ") " + fullName + " has been successfully created!" :
                "Create author error: Something went wrong!";
    }

    @Override
    @Transactional(readOnly = true)
    public String getAuthorById(long id) {
        Author author = authorRepositoryJpa.getAuthorById(id);
        return author == null ? "Author not found!" : author.getFullName();
    }

    @Override
    @Transactional(readOnly = true)
    public String getIdByAuthor(String fullName) {
        long id = authorRepositoryJpa.getIdByAuthor(new Author(fullName));
        return id == 0 ? "Author '" + fullName + "' not found in the library!" : "Author '" + fullName + "' has an id="
                + id;
    }

    @Override
    @Transactional
    public String updateAuthor(long id, String fullName) {
        boolean result = authorRepositoryJpa.updateAuthor(new Author(id, fullName));
        return result ? "Information about the author (" + "id=" + id + " " + fullName + ") has been updated!"
                : "Update error: Something went wrong!";
    }

    @Override
    @Transactional
    public String deleteAuthor(long id, String fullName) {
        boolean result = authorRepositoryJpa.deleteAuthor(new Author(id, fullName));
        return result ? "Author (id=" + id + " " + fullName + ") removed from the library"
                : "Delete error: Something went wrong!";
    }

    @Override
    @Transactional(readOnly = true)
    public String getAllAuthors() {
        List<Author> authorList = authorRepositoryJpa.getAllAuthors();
        String authorsString = "Authors in the library: ";
        for (int i = 0; i < authorList.size(); i++) {
            authorsString = authorList + " "
                    + authorList.get(i).getFullName() + (i < (authorList.size() - 1) ? ", " : ".");
        }
        return authorList.size() == 0 ? "Authors not found!" : authorsString;
    }

}
