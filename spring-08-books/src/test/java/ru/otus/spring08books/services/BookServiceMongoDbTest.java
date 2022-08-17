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
import ru.otus.spring08books.entities.Author;
import ru.otus.spring08books.entities.Book;
import ru.otus.spring08books.entities.Genre;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Класс BookServiceMongoDbTest содержит методы тестирования класса BookServiceMongoDb
 *
 * @see ru.otus.spring08books.services.BookServiceMongoDb
 */
@DataMongoTest
@DisplayName("BookServiceMongoDb must ")
@Import({AuthorServiceMongoDb.class, GenreServiceMongoDb.class, BookServiceMongoDb.class, CommentServiceMongoDb.class})
class BookServiceMongoDbTest {
    private final long EXPECTED_COUNT_BOOKS = 4;
    private final String EXPECTED_BOOK_TITLE_ONE = "New title for the test one";
    private final String EXPECTED_BOOK_TITLE_TWO = "New title for the test two";
    private final String EXPECTED_AUTHOR_NAME_ONE = "New author for the test one";
    private final String EXPECTED_GENRE_NAME_ONE = "New genre for the test one";

    @Autowired
    private BookServiceMongoDb bookServiceMongoDb;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void deleteTestBooks() {
        for (String titleBook : Arrays.asList(EXPECTED_BOOK_TITLE_ONE, EXPECTED_BOOK_TITLE_TWO)) {
            Book book = mongoTemplate.findOne(Query.query(Criteria.where("title")
                    .is(titleBook)), Book.class);
            if (book != null) {
                mongoTemplate.remove(book);
            }
        }
    }

    @BeforeEach
    void deleteTestAuthors() {
        Author author = mongoTemplate.findOne(Query.query(Criteria.where("fullName")
                .is(EXPECTED_AUTHOR_NAME_ONE)), Author.class);
        if (author != null) {
            mongoTemplate.remove(author);
        }
    }

    @BeforeEach
    void deleteTestGenres() {
        Genre genre = mongoTemplate.findOne(Query.query(Criteria.where("name")
                .is(EXPECTED_GENRE_NAME_ONE)), Genre.class);
        if (genre != null) {
            mongoTemplate.remove(genre);
        }
    }

    @Test
    @DisplayName("how many books in library")
    void shouldCountBooks() {
        long countBooks = bookServiceMongoDb.countBooks();
        assertThat(countBooks).isEqualTo(EXPECTED_COUNT_BOOKS);
    }

    @Test
    @DisplayName("create a new book and check by title")
    void shouldCreateNewBook() {
        bookServiceMongoDb.createNewBook(EXPECTED_BOOK_TITLE_ONE, EXPECTED_AUTHOR_NAME_ONE, EXPECTED_GENRE_NAME_ONE);
        Book book = mongoTemplate.findOne(Query.query(Criteria.where("title")
                .is(EXPECTED_BOOK_TITLE_ONE)), Book.class);
        assertThat(book.getTitle()).isEqualTo(EXPECTED_BOOK_TITLE_ONE);
    }

}