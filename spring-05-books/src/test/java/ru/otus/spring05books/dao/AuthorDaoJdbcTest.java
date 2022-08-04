package ru.otus.spring05books.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring05books.domain.Author;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Класс AuthorDaoJdbcTest тестирует методы класса AuthorDaoJdbc
 * тесты из JUnit5 (org.junit.jupiter)
 */
@DisplayName("To work with the list of authors, the DAO must")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    /**
     * Внедрение зависимости тестируемого класса
     */
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    /**
     * Поле ожидаемое количество авторов в базе
     */
    private static final int EXPECTED_AUTHORS_COUNT = 3;

    /**
     * Метод shouldCreateNewAuthor тестирует метод createAuthor
     * В созданной БД на начальном этапе 3 автора. Метод создает нового автора и через getAuthorById() получает его по id
     */
    @DisplayName("create a new author")
    @Test
    void shouldCreateNewAuthor() {
        Author expectedAuthor = new Author("New_author");
        authorDaoJdbc.createAuthor(expectedAuthor);
        long idExpectedAuthor = authorDaoJdbc.getIdByAuthor(expectedAuthor);
        Author actualAuthor = new Author(authorDaoJdbc.getAuthorById(idExpectedAuthor).getFullName());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    /**
     * Метод shouldUpdateAuthor тестирует updateAuthor
     */
    @DisplayName("update a new author")
    @Test
    void shouldUpdateAuthor() {
        Author authorForUpdate = new Author(1, "New_author");
        authorDaoJdbc.updateAuthor(authorForUpdate);
        assertThat(authorDaoJdbc.getAuthorById(1)).usingRecursiveComparison().isEqualTo(authorForUpdate);
    }

    /**
     * Метод shouldDeleteAuthor тестирует метод deleteAuthor
     */
    @DisplayName("deletes the author")
    @Test
    void shouldDeleteAuthor() {
        long newAuthorId = authorDaoJdbc.createAuthor(new Author("New_Author"));
        Author authorForDelete = new Author(newAuthorId, "New_Author");
        boolean result = authorDaoJdbc.deleteAuthor(authorForDelete);
        assertThat(result).isEqualTo(true);
    }

    /**
     * Метод shouldGetAuthorById выполняет тестирование метода getAuthorById
     * При создании БД под id=1 "John Bunyan"
     */
    @DisplayName("get the author by id")
    @Test
    void shouldGetAuthorById() {
        Author expectedAuthor = new Author(1, "John Bunyan");
        Author actualAuthor = authorDaoJdbc.getAuthorById(1);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    /**
     * Метод shouldGetIdByAuthor тестирует getIdByAuthor
     */
    @DisplayName("get the author's id")
    @Test
    void shouldGetIdByAuthor() {
        Author authorForGet = new Author(1, "John Bunyan");
        long authorId = authorDaoJdbc.getIdByAuthor(authorForGet);
        assertThat(authorId).usingRecursiveComparison().isEqualTo(authorForGet.getId());
    }

    /**
     * Метод shouldGetAllAuthors тестирует getAllAuthors
     */
    @DisplayName("get a list of authors")
    @Test
    void shouldGetAllAuthors() {
        List<Author> expectedAuthorsList = new ArrayList();
        expectedAuthorsList.add(new Author(1, "John Bunyan"));
        expectedAuthorsList.add(new Author(2, "Daniel Defoe"));
        expectedAuthorsList.add(new Author(3, "Gianni Rodari"));
        List<Author> actualAuthorsList = authorDaoJdbc.getAllAuthors();
        assertThat(actualAuthorsList.toString()).isEqualTo(expectedAuthorsList.toString());
    }

    /**
     * Метод shouldGetCountOfAuthors тестирует getCountOfAuthors
     */
    @DisplayName("return the expected number of authors from the library")
    @Test
    void shouldGetCountOfAuthors() {
        long actualAuthorsCount = authorDaoJdbc.getCountOfAuthors();
        assertThat(actualAuthorsCount).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }
}