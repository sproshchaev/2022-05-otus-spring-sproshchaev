package ru.otus.spring14books.sql.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring14books.sql.domain.Genre;
import ru.otus.spring14books.sql.repositories.GenreRepositorySource;

import java.util.List;

/**
 * Класс GenreServiceSql содержит методы для работы с репозиторием жанров книг
 *
 * @see ru.otus.spring14books.sql.repositories.GenreRepositorySource
 */
@Service
public class GenreServiceSql implements GenreService {

    private final GenreRepositorySource genreRepositorySource;

    @Autowired
    public GenreServiceSql(GenreRepositorySource genreRepositorySource) {
        this.genreRepositorySource = genreRepositorySource;
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
        List<Genre> authorList = genreRepositorySource.getGenreByName(genreName);
        return (authorList.size() == 0) ? null : authorList.get(0);
    }

    /**
     * Метод getAllGenre() возвращает все Жанры
     *
     * @return
     */
    @Override
    public List<Genre> getAllGenre() {
        return genreRepositorySource.findAll();
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
        return genreRepositorySource.count();
    }

}
