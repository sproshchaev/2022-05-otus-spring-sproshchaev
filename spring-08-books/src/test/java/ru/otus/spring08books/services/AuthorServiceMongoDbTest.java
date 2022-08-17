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

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Класс AuthorServiceMongoDbTest содержит методы тестирования класса AuthorServiceMongoDb
 * @see ru.otus.spring08books.services.AuthorServiceMongoDb
 */
@DataMongoTest
@Import(AuthorServiceMongoDb.class)
@DisplayName("AuthorServiceMongoDb must ")
class AuthorServiceMongoDbTest {
    private final long EXPECTED_COUNT_AUTHORS = 3;
    private final String EXPECTED_AUTHOR_NAME_ONE = "New author for the test one";
    private final String EXPECTED_AUTHOR_NAME_TWO = "New author for the test two";

    @Autowired
    private AuthorServiceMongoDb authorServiceMongoDb;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void deleteTestAuthors() {
        for (String authorFullName : Arrays.asList(EXPECTED_AUTHOR_NAME_ONE, EXPECTED_AUTHOR_NAME_TWO)) {
            Author author = mongoTemplate.findOne(Query.query(Criteria.where("fullName")
                    .is(authorFullName)), Author.class);
            if (author != null) {
                mongoTemplate.remove(author);
            }
        }
    }

    @Test
    @DisplayName("how many authors in library")
    void shouldCountAuthors() {
        Long countAuthors = authorServiceMongoDb.countAuthors();
        assertThat(countAuthors).isEqualTo(EXPECTED_COUNT_AUTHORS);
    }

    @Test
    @DisplayName("create a new author and check by name")
    void shouldCreateNewAuthor() {
        authorServiceMongoDb.createNewAuthor(EXPECTED_AUTHOR_NAME_ONE);
        Author author = mongoTemplate.findOne(Query.query(Criteria.where("fullName")
                .is(EXPECTED_AUTHOR_NAME_ONE)), Author.class);
        assertThat(author.getFullName()).isEqualTo(EXPECTED_AUTHOR_NAME_ONE);
    }

    @Test
    @DisplayName("create a new author and check by the count of authors")
    void shouldCreateNewAuthorAndCheckCount() {
        authorServiceMongoDb.createNewAuthor(EXPECTED_AUTHOR_NAME_ONE);
        Long countAuthorsAfter = mongoTemplate.count(new Query(), Author.class);
        assertThat(EXPECTED_COUNT_AUTHORS + 1).isEqualTo(countAuthorsAfter);
    }

    @Test
    @DisplayName("get id by author")
    void shouldGetIdByAuthor() {
        Author newAuthor = mongoTemplate.save(new Author(EXPECTED_AUTHOR_NAME_ONE));
        String result = authorServiceMongoDb.getIdByAuthor(EXPECTED_AUTHOR_NAME_ONE);
        assertThat(result.contains(newAuthor.getId())).isEqualTo(true);
    }

    @Test
    @DisplayName("get the author by id")
    void shouldGetAuthorById() {
        Author newAuthor = mongoTemplate.save(new Author(EXPECTED_AUTHOR_NAME_ONE));
        String result = authorServiceMongoDb.getAuthorById(newAuthor.getId());
        assertThat(result.contains(newAuthor.getFullName())).isEqualTo(true);
    }

    @Test
    @DisplayName("get all authors")
    void shouldGetAllAuthors() {
        String result = authorServiceMongoDb.getAllAuthors();
        Long countAuthor = 0L;
        for (Author author : mongoTemplate.findAll(Author.class)) {
            if (result.contains(author.getFullName())) {
                countAuthor++;
            };
        }
        assertThat(countAuthor).isEqualTo(EXPECTED_COUNT_AUTHORS);
    }

    @Test
    @DisplayName("changes the author's name by his id")
    void shouldUpdateAuthor() {
        Author newAuthor = mongoTemplate.save(new Author(EXPECTED_AUTHOR_NAME_ONE));
        authorServiceMongoDb.updateAuthor(newAuthor.getId(), EXPECTED_AUTHOR_NAME_TWO);
        assertThat(mongoTemplate.findById(newAuthor.getId(), Author.class).getFullName()).isEqualTo(EXPECTED_AUTHOR_NAME_TWO);
    }

    @Test
    @DisplayName("deletes the author by his id")
    void shouldDeleteAuthorById() {
        Author authorForDelete = mongoTemplate.save(new Author(EXPECTED_AUTHOR_NAME_ONE));
        authorServiceMongoDb.deleteAuthorById(authorForDelete.getId());
        assertThat(mongoTemplate.findOne(Query.query(Criteria.where("fullName").is(EXPECTED_AUTHOR_NAME_ONE)),
                Author.class)).isEqualTo(null);
    }

    @Test
    @DisplayName("deletes the author by his id (checking the number of records)")
    void shouldDeleteAuthorByIdCountRecords() {
        long countAuthorsBefore = mongoTemplate.count(new Query(), Author.class);
        Author authorForDelete = mongoTemplate.save(new Author(EXPECTED_AUTHOR_NAME_ONE));
        authorServiceMongoDb.deleteAuthorById(authorForDelete.getId());
        long countAuthorsAfter = mongoTemplate.count(new Query(), Author.class);
        assertThat((countAuthorsBefore == countAuthorsAfter)).isEqualTo(true);
    }

    @Test
    @DisplayName("gets the first author by full name")
    void shouldGetFirstAuthorByFullName() {
        Author authorExpected = authorServiceMongoDb.getFirstAuthorByFullName(EXPECTED_AUTHOR_NAME_ONE);
        Author authorActual = mongoTemplate.findOne(Query.query(Criteria.where("fullName").is(EXPECTED_AUTHOR_NAME_ONE)),
                Author.class);
        assertThat(authorExpected.equals(authorActual)).isEqualTo(true);
    }

    @Test
    @DisplayName("gets the first author by full name (twice)")
    void shouldGetFirstAuthorByFullNameTwice() {
        Author authorForGetOne = authorServiceMongoDb.getFirstAuthorByFullName(EXPECTED_AUTHOR_NAME_ONE);
        Author authorForGetTwo = authorServiceMongoDb.getFirstAuthorByFullName(EXPECTED_AUTHOR_NAME_ONE);
        assertThat(authorForGetOne.equals(authorForGetTwo)).isEqualTo(true);
    }
}