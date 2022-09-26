package ru.otus.spring14books.sql.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring14books.sql.domain.Author;
import ru.otus.spring14books.sql.repositories.AuthorRepositorySource;

import java.util.List;

/**
 * Класс AuthorService содержит методы для работы с репозиторием авторов библиотеки
 *
 * @see ru.otus.spring14books.sql.repositories.AuthorRepositorySource
 */
@Service
public class AuthorServiceSql implements AuthorService {

    private final AuthorRepositorySource authorRepositorySource;

    @Autowired
    public AuthorServiceSql(AuthorRepositorySource authorRepositorySource) {
        this.authorRepositorySource = authorRepositorySource;
    }

    /**
     * Метод getFirstAuthorByFullName возвращает первого автора из списка, авторов с одинаковым значением поля fullName
     * Метод может изменять данные
     *
     * @param authorFullName
     * @return
     */
    @Transactional
    @Override
    public Author getFirstAuthorByFullName(String authorFullName) {
        List<Author> authorList = authorRepositorySource.getAuthorByFullName(authorFullName);
        return (authorList.size() == 0) ? authorRepositorySource.save(new Author(authorFullName)) : authorList.get(0);
    }

    /**
     * Метод getAllAuthors получает список всех авторов из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<Author> getAllAuthors() {
        return authorRepositorySource.findAll();
    }

    /**
     * Число авторов в библиотеке
     * Метод не изменяет данные
     *
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public long countAuthors() {
        return authorRepositorySource.count();
    }

}