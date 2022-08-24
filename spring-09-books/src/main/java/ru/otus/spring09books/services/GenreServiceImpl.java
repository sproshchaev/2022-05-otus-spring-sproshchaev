package ru.otus.spring09books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring09books.repositories.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Число жанров книг в библиотеке
     *
     * @return
     */
    @Override
    public long countGenres() {
        return genreRepository.count();
    }
}