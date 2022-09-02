package ru.otus.spring10books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring10books.domain.Genre;
import ru.otus.spring10books.repositories.GenreRepository;

import java.util.List;

/**
 * Класс GenreServiceImpl содержит методы для работы с репозиторием жанров книг
 *
 * @see ru.otus.spring10books.repositories.GenreRepository
 */
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Метод getFirstGenreByName возвращает первый жанр из списка с одинаковым значением поля name
     * Метод может изменять данные
     *
     * @param genreName
     * @return
     */
    @Transactional
    @Override
    public Genre getFirstGenreByName(String genreName) {
        List<Genre> authorList = genreRepository.getGenreByName(genreName);
        return (authorList.size() == 0) ? genreRepository.save(new Genre(genreName)) : authorList.get(0);
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