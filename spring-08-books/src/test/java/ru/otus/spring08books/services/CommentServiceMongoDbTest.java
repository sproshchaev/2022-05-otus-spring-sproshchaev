package ru.otus.spring08books.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс CommentServiceMongoDbTest содержит методы тестирования класса CommentServiceMongoDb
 * @see ru.otus.spring08books.services.CommentServiceMongoDb
 */
@DataMongoTest
@DisplayName("CommentServiceMongoDb must ")
@Import({AuthorServiceMongoDb.class, GenreServiceMongoDb.class, BookServiceMongoDb.class, CommentServiceMongoDb.class})
class CommentServiceMongoDbTest {
    private final long EXPECTED_COUNT_COMMENTS = 4;
    private final String EXPECTED_COMMENT_TEXT_ONE = "New comment for the test one";
    private final String EXPECTED_COMMENT_TEXT_TWO = "New comment for the test two";

    @Autowired
    private CommentServiceMongoDb commentServiceMongoDb;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void countComments() {
    }

    @Test
    void createCommentByIdBook() {
    }

    @Test
    void getCommentById() {
    }

    @Test
    void getAllCommentsBookById() {
    }

    @Test
    void updateCommentById() {
    }

    @Test
    void deleteCommentById() {
    }

}