package ru.otus.spring06books.repositories;

import ru.otus.spring06books.entities.Genre;

import java.util.List;

/**
 * Интерфейс GenreRepository содержит методы для сущности Author
 *
 * @see ru.otus.spring06books.entities.Genre
 */
public interface GenreRepository {

    long createGenre(Genre genre);

    boolean updateGenre(Genre genre);

    boolean deleteGenre(Genre genre);

    Genre getGenreById(long id);

    long getIdByGenre(Genre genre);

    List<Genre> getAllGenres();

    int getCountOfGenres();
}
