package ru.otus.spring09books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring09books.domain.Author;
import ru.otus.spring09books.repositories.AuthorRepository;

import java.util.List;

/**
 * Класс AuthorService содержит методы для работы с репозиторием авторов библиотеки
 *
 * @see ru.otus.spring09books.repositories.AuthorRepository
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
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
        List<Author> authorList = authorRepository.getAuthorByFullName(authorFullName);
        return (authorList.size() == 0) ? authorRepository.save(new Author(authorFullName)) : authorList.get(0);
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
        return authorRepository.findAll();
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
        return authorRepository.count();
    }

}