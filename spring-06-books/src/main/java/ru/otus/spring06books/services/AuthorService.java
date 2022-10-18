package ru.otus.spring06books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06books.entities.Author;
import ru.otus.spring06books.repositories.AuthorRepositoryJpa;

import java.util.List;

/**
 * Класс AuthorService содержит методы сервиса работы с репозиторием Авторов библиотеки
 */
@Service
public class AuthorService {

    private final AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    public AuthorService(AuthorRepositoryJpa authorRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
    }

    /**
     * Метод createNewAuthor (Crud)
     * Аннотация @Transactional - метод изменяет данные
     *
     * @param fullName
     * @return
     */
    @Transactional
    public String createNewAuthor(String fullName) {
        long id = authorRepositoryJpa.createAuthor(new Author(fullName)).getId();
        return id != 0 ? "New author (" + id + ") " + fullName + " has been successfully created!" :
                "Create author error: Something went wrong!";
    }

    /**
     * Метод getAuthorById получает данные об авторе по его id (cRud)
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Метод не подразумевает изменения данных в БД, используется рекомендуемая аннотация @Transactional(readOnly = true)
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public String getAuthorById(long id) {
        Author author = authorRepositoryJpa.getAuthorById(1);
        return author == null ? "Author not found!" : author.getFullName();
    }

    /**
     * Метод getIdByAuthor возвращает id для полного имени данного автора, если он есть в библиотеке
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     * Метод не подразумевает изменения данных в БД, используется рекомендуемая аннотация @Transactional(readOnly = true)
     *
     * @param fullName
     * @return
     */
    @Transactional(readOnly = true)
    public String getIdByAuthor(String fullName) {
        long id = authorRepositoryJpa.getIdByAuthor(new Author(fullName));
        return id == 0 ? "Author '" + fullName + "' not found in the library!" : "Author '" + fullName + "' has an id="
                + id;
    }

    /**
     * Метод updateAuthor обновляет данные об авторе в библиотеке (crUd)
     * Аннотация @Transactional - метод изменяет данные
     *
     * @param id
     * @param fullName
     * @return
     */
    @Transactional
    public String updateAuthor(long id, String fullName) {
        boolean result = authorRepositoryJpa.updateAuthor(new Author(id, fullName));
        return result ? "Information about the author (" + "id=" + id + " " + fullName + ") has been updated!"
                : "Update error: Something went wrong!";
    }

    /**
     * Метод deleteAuthor удаляет данные об авторе в библиотеке (cruD)
     * Аннотация @Transactional - метод изменяет данные
     *
     * @param id
     * @param fullName
     * @return
     */
    @Transactional
    public String deleteAuthor(long id, String fullName) {
        boolean result = authorRepositoryJpa.deleteAuthor(new Author(id, fullName));
        return result ? "Author (id=" + id + " " + fullName + ") removed from the library"
                : "Delete error: Something went wrong!";
    }

    /**
     * Метод getAllAuthors получает список всех авторов из библиотеки (cRud)
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     *
     * @return
     */
    @Transactional(readOnly = true)
    public String getAllAuthors() {
        List<Author> authorList = authorRepositoryJpa.getAllAuthors();
        String authorsString = "Authors in the library: ";
        for (int i = 0; i < authorList.size(); i++) {
            authorsString = authorList + " " + authorList.get(i).getFullName() + (i < (authorList.size() - 1) ? ", " : ".");
        }
        return authorList.size() == 0 ? "Authors not found!" : authorsString;
    }

}
