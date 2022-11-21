package ru.otus.spring06books.services;

public interface GenreService {
    String createGenre(String name);

    String getIdByGenre(String name);

    String updateGenre(long id, String name);

    String deleteGenre(long id, String name);

    String getGenreById(long id);

    String getAllGenres();
}
