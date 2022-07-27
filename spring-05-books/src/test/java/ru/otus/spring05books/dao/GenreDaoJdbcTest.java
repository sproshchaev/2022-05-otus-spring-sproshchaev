package ru.otus.spring05books.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring05books.domain.Author;
import ru.otus.spring05books.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Класс GenreDaoJdbcTest тестирует методы класса GenreDaoJdbc
 * тесты из JUnit5 (org.junit.jupiter)
 */
@DisplayName("To work with the list of genres, the DAO must")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    /**
     * Внедрение зависимости тестируемого класса
     */
    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    /**
     * Поле ожидаемое количество жанров в базе
     */
    private static final int EXPECTED_GENRES_COUNT = 5;

    /**
     * Метод shouldCreateGenre тестирует метод createGenre
     * В созданной БД на начальном этапе 5 жанров. Метод создает новый жанр и через getGenreById() получает его по id
     */
    @DisplayName("create a new genre")
    @Test
    void shouldCreateGenre() {
        Genre expectedGenre = new Genre("New_genre");
        genreDaoJdbc.createGenre(expectedGenre);
        long idExpectedGenre = genreDaoJdbc.getIdByGenre(expectedGenre);
        Genre actualGenre = new Genre(genreDaoJdbc.getGenreById(idExpectedGenre).getName());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    /**
     * Метод shouldUpdateGenre тестирует updateGenre
     */
    @DisplayName("update a new genre")
    @Test
    void shouldUpdateGenre() {
        Genre genreForUpdate = new Genre(1, "New_genre");
        genreDaoJdbc.updateGenre(genreForUpdate);
        assertThat(genreDaoJdbc.getGenreById(1)).usingRecursiveComparison().isEqualTo(genreForUpdate);
    }

    /**
     * Метод shouldDeleteGenre тестирует метод deleteGenre
     */
    @DisplayName("deletes the genre")
    @Test
    void shouldDeleteGenre() {
        long newGenreId = genreDaoJdbc.createGenre(new Genre("New_Genre"));
        Genre genreForDelete = new Genre(newGenreId, "New_Genre");
        boolean result = genreDaoJdbc.deleteGenre(genreForDelete);
        assertThat(result).isEqualTo(true);
    }

    /**
     * Метод shouldGetGenreById выполняет тестирование метода getGenreById
     * При создании БД под id=1 "History"
     */
    @DisplayName("get the genre by id")
    @Test
    void shouldGetGenreById() {
        Genre expectedGenre = new Genre(1, "History");
        Genre actualGenre = genreDaoJdbc.getGenreById(1);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    /**
     * Метод shouldGetIdByGenre тестирует getIdByGenre
     */
    @DisplayName("get the genre by id")
    @Test
    void shouldGetIdByGenre() {
        Genre genreForGet = new Genre(1, "History");
        long genreId = genreDaoJdbc.getIdByGenre(genreForGet);
        assertThat(genreId).usingRecursiveComparison().isEqualTo(genreForGet.getId());
    }

    /**
     * Метод shouldGetAllGenres тестирует getAllGenres
     */
    @DisplayName("get a list of genres")
    @Test
    void shouldGetAllGenres() {
        List<Genre> expectedGenresList = new ArrayList();
        expectedGenresList.add(new Genre(1, "History"));
        expectedGenresList.add(new Genre(2, "Classic"));
        expectedGenresList.add(new Genre(3, "Fantasy"));
        expectedGenresList.add(new Genre(4, "Autobiography"));
        expectedGenresList.add(new Genre(5, "Fiction"));
        List<Genre> actualGenresList = genreDaoJdbc.getAllGenres();
        assertThat(actualGenresList.toString()).isEqualTo(expectedGenresList.toString());
    }

    /**
     * Метод shouldGetCountOfGenres тестирует getCountOfGenres
     */
    @Test
    void shouldGetCountOfGenres() {
        long actualGenresCount = genreDaoJdbc.getCountOfGenres();
        assertThat(actualGenresCount).isEqualTo(EXPECTED_GENRES_COUNT);
    }
}