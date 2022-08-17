package ru.otus.spring08books.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.spring08books.entities.Genre;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Класс GenreServiceMongoDbTest содержит методы тестирования класса GenreServiceMongoDb
 * @see ru.otus.spring08books.services.GenreServiceMongoDb
 */
@DataMongoTest
@Import(GenreServiceMongoDb.class)
@DisplayName("GenreServiceMongoDb must ")
class GenreServiceMongoDbTest {

    private final long EXPECTED_COUNT_GENRES = 5;
    private final String EXPECTED_GENRE_NAME_ONE = "New genre for the test one";
    private final String EXPECTED_GENRE_NAME_TWO = "New genre for the test two";

    @Autowired
    private GenreServiceMongoDb genreServiceMongoDb;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void deleteTestAuthors() {
        for (String authorFullName : Arrays.asList(EXPECTED_GENRE_NAME_ONE, EXPECTED_GENRE_NAME_TWO)) {
            Genre genre = mongoTemplate.findOne(Query.query(Criteria.where("name")
                    .is(authorFullName)), Genre.class);
            if (genre != null) {
                mongoTemplate.remove(genre);
            }
        }
    }

    @Test
    @DisplayName("how many genres in library")
    void shouldCountGenres() {
        Long countGenres = genreServiceMongoDb.countGenres();
        assertThat(countGenres).isEqualTo(EXPECTED_COUNT_GENRES);
    }

    @Test
    void createGenre() {
    }

    @Test
    void getIdByGenre() {
    }

    @Test
    void getGenreById() {
    }

    @Test
    void getAllGenres() {
    }

    @Test
    void updateGenre() {
    }

    @Test
    void deleteGenreById() {
    }

    @Test
    void getFirstGenreByName() {
    }

}