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
    void deleteTestGenres() {
        for (String genreName : Arrays.asList(EXPECTED_GENRE_NAME_ONE, EXPECTED_GENRE_NAME_TWO)) {
            Genre genre = mongoTemplate.findOne(Query.query(Criteria.where("name")
                    .is(genreName)), Genre.class);
            if (genre != null) {
                mongoTemplate.remove(genre);
            }
        }
    }

    @Test
    @DisplayName("how many genres in library")
    void shouldCountGenres() {
        long countGenres = genreServiceMongoDb.countGenres();
        assertThat(countGenres).isEqualTo(EXPECTED_COUNT_GENRES);
    }

    @Test
    @DisplayName("create a new genre and check by name")
    void shouldCreateNewGenre() {
        genreServiceMongoDb.createGenre(EXPECTED_GENRE_NAME_ONE);
        Genre genre = mongoTemplate.findOne(Query.query(Criteria.where("name")
                .is(EXPECTED_GENRE_NAME_ONE)), Genre.class);
        assertThat(genre.getName()).isEqualTo(EXPECTED_GENRE_NAME_ONE);
    }
}