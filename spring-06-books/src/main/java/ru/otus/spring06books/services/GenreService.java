package ru.otus.spring06books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06books.entities.Genre;
import ru.otus.spring06books.repositories.GenreRepositoryJpa;

import java.util.List;

/**
 * Класс GenreService содержит методы сервиса работы с репозиторием Жанров библиотеки
 */
@Service
public class GenreService {

    private final GenreRepositoryJpa genreRepositoryJpa;

    @Autowired
    public GenreService(GenreRepositoryJpa genreRepositoryJpa) {
        this.genreRepositoryJpa = genreRepositoryJpa;
    }

    /**
     * Метод createGenre - создает наименование нового жанра книг в библиотеке
     * Аннотация @Transactional - метод изменяет данные
     *
     * @param name
     * @return
     */
    @Transactional
    public String createGenre(String name) {
        long id = genreRepositoryJpa.createGenre(new Genre(name));
        return "New genre (" + id + ") " + name + " has been successfully created!";
    }

    /**
     * Метод getIdByGenre возвращает id для жанра, если он есть в библиотеке
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     *
     * @param name
     * @return
     */
    @Transactional(readOnly = true)
    public String getIdByGenre(String name) {
        long id = genreRepositoryJpa.getIdByGenre(new Genre(name));
        return id == 0 ? "Genre '" + name + "' not found in the library!" : "Genre '" + name + "' has an id=" + id;
    }

    /**
     * Метод updateGenre обновляет данные о жанре в библиотеке
     * Аннотация @Transactional - метод изменяет данные
     *
     * @param id
     * @param name
     * @return
     */
    @Transactional
    public String updateGenre(long id, String name) {
        boolean result = genreRepositoryJpa.updateGenre(new Genre(id, name));
        return result ? "Information about the genre (" + "id=" + id + " " + name + ") has been updated!"
                : "Update error: Something went wrong!";
    }

    /**
     * Метод deleteGenre удаляет данные о жанре в библиотеке
     * Аннотация @Transactional - метод изменяет данные
     *
     * @param id
     * @param name
     * @return
     */
    @Transactional
    public String deleteGenre(long id, String name) {
        boolean result = genreRepositoryJpa.deleteGenre(new Genre(id, name));
        return result ? "Genre (id=" + id + " " + name + ") removed from the library"
                : "Delete error: Something went wrong!";
    }

    /**
     * Метод getGenreById получает данные о жанре по его id
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public String getGenreById(long id) {
        Genre genre = genreRepositoryJpa.getGenreById(id);
        return genre == null ? "Genre not found!" : genre.getId() + " " + genre.getName();
    }

    /**
     * Метод getAllGenres получает список всех жанров из библиотеки (cRud)
     * Аннотация @Transactional(readOnly = true) - метод не изменяет данные
     *
     * @return
     */
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
