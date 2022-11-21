package ru.otus.spring06books.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring06books.entities.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс GenreRepositoryJpaTest выполняет тестирование методов класса GenreRepositoryJpa
 * Аннотация @DataJpaTest создает кусок контекста слоя persist, TestEntityManager, транзакцию в начале каждого теста
 * тесты из JUnit5 (org.junit.jupiter)
 */
@DisplayName("Repository for working with genres ")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {
    /**
     * Внедрение зависимости тестируемого класса
     */
    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;
    @Autowired
    private TestEntityManager entityManager;
    /**
     * Поле ожидаемое количество жанров в базе
     */
    private static final int EXPECTED_GENRES_COUNT = 5;

    /**
     * Метод shouldCreateGenre тестирует метод createGenre
     * В созданной БД на начальном этапе EXPECTED_GENRES_COUNT жанров. Метод создает новый жанр и через getGenreById() получает его по id
     */
    @DisplayName("create a new genre")
    @Test
    void shouldCreateGenre() {
        Genre expectedGenre = new Genre("New_genre");
        long idExpectedGenre = genreRepositoryJpa.createGenre(expectedGenre);
        Genre actualGenre = entityManager.find(Genre.class, idExpectedGenre);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    /**
     * Метод shouldUpdateGenre тестирует updateGenre
     */
    @DisplayName("update a new genre")
    @Test
    void shouldUpdateGenre() {
        Genre genreForUpdate = new Genre(1, "New_genre");
        genreRepositoryJpa.updateGenre(genreForUpdate);
        assertThat(genreRepositoryJpa.getGenreById(1)).usingRecursiveComparison().isEqualTo(genreForUpdate);
    }

    /**
     * Метод shouldDeleteGenre тестирует метод deleteGenre
     */
    @DisplayName("deletes the genre")
    @Test
    void shouldDeleteGenre() {
        long newGenreId = genreRepositoryJpa.createGenre(new Genre("New_Genre"));
        Genre genreForDelete = new Genre(newGenreId, "New_Genre");
        boolean result = genreRepositoryJpa.deleteGenre(genreForDelete);
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
        Genre actualGenre = genreRepositoryJpa.getGenreById(1);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    /**
     * Метод shouldGetIdByGenre тестирует getIdByGenre
     */
    @DisplayName("get the genre by id")
    @Test
    void shouldGetIdByGenre() {
        Genre genreForGet = new Genre(1, "History");
        long genreId = genreRepositoryJpa.getIdByGenre(genreForGet);
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
        List<Genre> actualGenresList = genreRepositoryJpa.getAllGenres();
        assertThat(actualGenresList.toString()).isEqualTo(expectedGenresList.toString());
    }

    /**
     * Метод shouldGetCountOfGenres тестирует getCountOfGenres
     * В константе EXPECTED_GENRES_COUNT записано число жанров в библиотеке с которым происходит сравнение
     */
    @DisplayName("gets the number of genres")
    @Test
    void getCountOfGenres() {
        long actualGenresCount = genreRepositoryJpa.getCountOfGenres();
        assertThat(actualGenresCount).isEqualTo(EXPECTED_GENRES_COUNT);
    }
}