package ru.otus.spring08books.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс LibraryServiceMongoDbTest содержит методы тестирования класса LibraryServiceMongoDbTest
 * @see ru.otus.spring08books.services.LibraryServiceMongoDb
 */
@DataMongoTest
@DisplayName("LibraryServiceMongoDb must ")
@Import({AuthorServiceMongoDb.class, GenreServiceMongoDb.class, BookServiceMongoDb.class, CommentServiceMongoDb.class,
        LibraryServiceMongoDb.class})
class LibraryServiceMongoDbTest {

    private final long EXPECTED_COUNT_AUTHORS = 3;
    private final long EXPECTED_COUNT_GENRES = 5;
    private final long EXPECTED_COUNT_BOOKS = 4;
    private final long EXPECTED_COUNT_COMMENTS = 4;

    @Autowired
    private AuthorServiceMongoDb authorServiceMongoDb;
    @Autowired
    private GenreServiceMongoDb genreServiceMongoDb;
    @Autowired
    private BookServiceMongoDb bookServiceMongoDb;
    @Autowired
    private CommentServiceMongoDb commentServiceMongoDb;
    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    void aboutLibrary() {
    }
}