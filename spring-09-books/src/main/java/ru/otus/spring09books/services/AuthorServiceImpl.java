package ru.otus.spring09books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring09books.repositories.AuthorRepository;

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
     * Число авторов в библиотеке
     *
     * @return
     */
    @Override
    public long countAuthors() {
        return authorRepository.count();
    }
}