package ru.otus.spring09books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring09books.repositories.GenreRepository;

/**
 * Класс GenreServiceImpl содержит методы для работы с репозиторием жанров книг
 *
 * @see ru.otus.spring09books.repositories.GenreRepository
 */
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Число жанров книг в библиотеке
     * Метод не изменяет данные
     *
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public long countGenres() {
        return genreRepository.count();
    }

}