package ru.otus.spring06books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06books.entities.Genre;
import ru.otus.spring06books.repositories.GenreRepositoryJpa;

import java.util.List;

/**
 * Класс GenreServiceImpl содержит методы сервиса работы с репозиторием Жанров библиотеки
 */
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepositoryJpa genreRepositoryJpa;

    @Autowired
    public GenreServiceImpl(GenreRepositoryJpa genreRepositoryJpa) {
        this.genreRepositoryJpa = genreRepositoryJpa;
    }

    @Override
    @Transactional
    public String createGenre(String name) {
        long id = genreRepositoryJpa.createGenre(new Genre(name));
        return "New genre (" + id + ") " + name + " has been successfully created!";
    }

    @Override
    @Transactional(readOnly = true)
    public String getIdByGenre(String name) {
        long id = genreRepositoryJpa.getIdByGenre(new Genre(name));
        return id == 0 ? "Genre '" + name + "' not found in the library!" : "Genre '" + name + "' has an id=" + id;
    }

    @Override
    @Transactional
    public String updateGenre(long id, String name) {
        boolean result = genreRepositoryJpa.updateGenre(new Genre(id, name));
        return result ? "Information about the genre (" + "id=" + id + " " + name + ") has been updated!"
                : "Update error: Something went wrong!";
    }

    @Override
    @Transactional
    public String deleteGenre(long id, String name) {
        boolean result = genreRepositoryJpa.deleteGenre(new Genre(id, name));
        return result ? "Genre (id=" + id + " " + name + ") removed from the library"
                : "Delete error: Something went wrong!";
    }

    @Override
    @Transactional(readOnly = true)
    public String getGenreById(long id) {
        Genre genre = genreRepositoryJpa.getGenreById(id);
        return genre == null ? "Genre not found!" : genre.getId() + " " + genre.getName();
    }

    @Override
    @Transactional(readOnly = true)
    public String getAllGenres() {
        List<Genre> genreList = genreRepositoryJpa.getAllGenres();
        String genersString = "Genres in the library: ";
        for (int i = 0; i < genreList.size(); i++) {
            genersString = genersString + " " + genreList.get(i).getName() + (i < (genreList.size() - 1) ? ", " : ".");
        }
        return genreList.size() == 0 ? "Genres not found!" : genersString;
    }

}
