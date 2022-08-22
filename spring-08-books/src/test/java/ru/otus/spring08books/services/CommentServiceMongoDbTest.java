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
import ru.otus.spring08books.entities.Book;
import ru.otus.spring08books.entities.Comment;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Класс CommentServiceMongoDbTest содержит методы тестирования класса CommentServiceMongoDb
 *
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

    @BeforeEach
    void deleteTestComment() {
        for (String commentText : Arrays.asList(EXPECTED_COMMENT_TEXT_ONE, EXPECTED_COMMENT_TEXT_TWO)) {
            Comment comment = mongoTemplate.findOne(Query.query(Criteria.where("commentText")
                    .is(commentText)), Comment.class);
            if (comment != null) {
                mongoTemplate.remove(comment);
            }
        }
    }

    @Test
    @DisplayName("how many comments in library")
    void shouldCountAuthors() {
        long countComments = commentServiceMongoDb.countComments();
        assertThat(countComments).isEqualTo(EXPECTED_COUNT_COMMENTS);
    }

    @Test
    @DisplayName("create a new comment by id book")
    void shouldCreateCommentByIdBook() {
        Book book = mongoTemplate.findAll(Book.class).get(0);
        commentServiceMongoDb.createCommentByIdBook(book.getId(), EXPECTED_COMMENT_TEXT_ONE);
        Comment comment = mongoTemplate.findOne(Query.query(Criteria.where("commentText")
                .is(EXPECTED_COMMENT_TEXT_ONE)), Comment.class);
        assertThat(comment.getCommentText()).isEqualTo(EXPECTED_COMMENT_TEXT_ONE);
    }
}