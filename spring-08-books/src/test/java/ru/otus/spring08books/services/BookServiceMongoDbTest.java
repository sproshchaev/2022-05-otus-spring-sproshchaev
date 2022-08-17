package ru.otus.spring08books.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс BookServiceMongoDbTest содержит методы тестирования класса BookServiceMongoDb
 * @see ru.otus.spring08books.services.BookServiceMongoDb
 */
@DataMongoTest
@DisplayName("BookServiceMongoDb must ")
@Import({AuthorServiceMongoDb.class, GenreServiceMongoDb.class, BookServiceMongoDb.class, CommentServiceMongoDb.class})
class BookServiceMongoDbTest {
    private final long EXPECTED_COUNT_BOOKS = 4;
    private final String EXPECTED_BOOK_TITLE_ONE = "New title for the test one";
    private final String EXPECTED_BOOK_TITLE_TWO = "New title for the test two";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void countBooks() {
    }

    @Test
    void createNewBook() {
    }

    @Test
    void getIdByBook() {
    }

    @Test
    void getBookById() {
    }

    @Test
    void findBookById() {
    }

    @Test
    void getAllBook() {
    }

    @Test
    void updateBookById() {
    }

    @Test
    void deleteBookById() {
    }

}